package com.example.loginmvvm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.loginmvvm.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    LoginViewModel loginViewModel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // instancia o viewModel
        loginViewModel = new LoginViewModel(this);

        // vincula a view ao viewModel
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(loginViewModel);
        binding.executePendingBindings();

        //inicia o binding dos campos e variáveis
        bindLoadingState();
    }

    public void bindLoadingState(){
        //observa a booleana que inicia a progress dialog e descarrega ao final da consulta na API
        binding.getViewModel().loadingState.addOnPropertyChangedCallback(
                new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable observable, int i) {
                        boolean isLoading = binding.getViewModel().loadingState.get();
                        if (isLoading){
                            progressDialog =
                                    ProgressDialog.show(LoginActivity.this,"Carregando",
                                            "Aguarde...",true, false);
                        } else {
                            progressDialog.dismiss();
                        }
                    }
                });
        //observa a variável com o retorno de sucesso da API
        binding.getViewModel().retorno.addOnPropertyChangedCallback(
                new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable observable, int i) {
                        String retorno = binding.getViewModel().retorno.get();
                        if (retorno!=null && !retorno.equals("")){
                            //envia os dados do aluno retornados para a activity principal
                            Intent it = new Intent(LoginActivity.this, MainActivity.class);
                            it.putExtra("retorno",retorno);
                            startActivity(it);
                        }
                    }
                });
        //observa a variável com o retorno de erro da API
        binding.getViewModel().erro.addOnPropertyChangedCallback(
                new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable observable, int i) {
                        String erro = binding.getViewModel().erro.get();
                        //exibe a mensagem de retorno da API caso ocorra algum problema com os dados ou consulta ao banco
                        if (erro!=null && !erro.equals("")){
                            Toast.makeText(getApplicationContext(),erro,Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    //função que trata o clique do botão de login e inicia a conexão com a API.
    public void onLoginClick(View view) {
        binding.getViewModel().onLoginClick();
    }
}
