package com.example.loginmvvm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
//Classe que exibe os dados retornados na tela
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView txtNome = findViewById(R.id.txtNome);
        TextView txtCpf = findViewById(R.id.txtCpf);
        TextView txtEndereco = findViewById(R.id.txtEndereco);
        TextView txtBairro = findViewById(R.id.txtBairro);
        TextView txtMunicipio = findViewById(R.id.txtMunicipio);
        TextView txtUF = findViewById(R.id.txtUF);
        TextView txtTelefone = findViewById(R.id.txtTelefone);
        TextView txtEmail = findViewById(R.id.txtEmail);
        TextView txtSenha = findViewById(R.id.txtSenha);
        Bundle bundle = getIntent().getExtras();

        if(bundle!= null)
        {
            //Utiliza framework Gson da Goole para dar o parse da Json string da API para o objeto aluno
            //carrega os dados na tela e exibe a mensagem de sucesso ao usuário
            Aluno aluno = new Gson().fromJson(bundle.getString("retorno"),Aluno.class);
            txtNome.setText(aluno.getNOME());
            txtCpf.setText(aluno.getCPF());
            txtEndereco.setText(aluno.getENDERECO());
            txtBairro.setText(aluno.getBAIRRO());
            txtMunicipio.setText(aluno.getMUNICIPIO());
            txtUF.setText(aluno.getUF());
            txtTelefone.setText(aluno.getTELEFONE());
            txtEmail.setText(aluno.getEMAIL());
            txtSenha.setText(aluno.getSENHA());
            Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_LONG).show();
        }
    }
}
