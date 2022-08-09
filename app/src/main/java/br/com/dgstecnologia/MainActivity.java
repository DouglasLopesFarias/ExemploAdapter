package br.com.dgstecnologia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvCidades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Cidade> cidades = new ArrayList<Cidade>();
        CidadeRepository cidadeRepository = CidadeRepository.getInstance(this);
        cidades = cidadeRepository.getAll();

        /*for(int i=1; i<=5; i++){
            Cidade cidade = new Cidade(i, "cidade "+ i, "pr");
            cidades.add(cidade);
        }*/


        lvCidades = findViewById(R.id.lvCidades);

        ArrayAdapter ad = new CidadeAdapter(this,R.layout.lista_cidades, cidades);
        lvCidades.setAdapter(ad);



    }

}