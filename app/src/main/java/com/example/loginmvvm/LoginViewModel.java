package com.example.loginmvvm;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginViewModel {

    private Context context;
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    ObservableField<String> retorno = new ObservableField<>();
    ObservableField<String> erro = new ObservableField<>();
    ObservableBoolean loadingState = new ObservableBoolean();

    LoginViewModel(Context ctx){
        context = ctx;
    }

    void onLoginClick() {
        //verifica se os campos de email e senha estão vazios ou nulos para proseguir com a chamada a API.
        if ((email.get() == null || password.get() == null) || (email.get().isEmpty() || password.get().isEmpty()) ){
            return;
        }

        // inicia o progress dialog da activity principal
        loadingState.set(true);
        //inicia a chamada
        callWebService();
    }

    private void callWebService() {
        //url do serviço GET do aluno passando o usuário e senha
        String url = "http://cigam-mg.ddns.net:8080/api/Alunos?email="+email.get()+"&senha="+password.get();
        //criando a fila de requisições com o framework Volley da google
        RequestQueue queue = Volley.newRequestQueue(context);

        try {
            //cria o request ao servidor passando método GET, url do serviço e aguarda o listener do retorno da API
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //retorno ok da API fecha o progress dialog na activity anterior via binding e atualiza o retorno
                    loadingState.set(false);
                    retorno.set(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    NetworkResponse response = error.networkResponse;
                    try {
                        //retorno de erro da API fecha o progress dialog na activity anterior via binding e envia a mensagem de erro da API
                        loadingState.set(false);
                        erro.set(new JSONObject(new String(response.data)).get("Message").toString());
                    }catch (Exception e){
                        loadingState.set(false);
                        e.printStackTrace();
                    }
                }
            }) {
                @Override
                public Map<String, String> getHeaders() {
                    //cria o cabeçalho da requisição
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json;charset=UTF-8");
                    return headers;
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    try {
                        //realiza o parse da resposta da API e lança para uma resposta ou erro
                        return super.parseNetworkResponse(response);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            //configura o tempo ms que irá aguardar para receber um retorno da API e quantidades de tentativas
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            //adiciona na fila de requisições a atual chamada
            queue.add(stringRequest);
        } catch (Exception e) {
            loadingState.set(false);
            e.printStackTrace();
        }
    }
}
