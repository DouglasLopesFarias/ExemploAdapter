package br.com.dgstecnologia;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CidadeAdapter extends ArrayAdapter {

    private final LayoutInflater inflater;
    private final int resourceId;

    public CidadeAdapter(MainActivity activity, int listaModeloCidade, List<Cidade> cidades){
        super(activity, listaModeloCidade, cidades);
        this.inflater = LayoutInflater.from(activity);
        this.resourceId = listaModeloCidade;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Cidade cidade = (Cidade) getItem(position);
        convertView = inflater.inflate(resourceId, parent, false);

        TextView txvCodigo = convertView.findViewById(R.id.txvCodigo);
        txvCodigo.setText(String.valueOf(cidade.getCodigo()));

        TextView txvNome = convertView.findViewById(R.id.txvNome);
        txvNome.setText(cidade.getNome());


        TextView txvUf = convertView.findViewById(R.id.txvUf);
        txvUf.setText(cidade.getUf());


        return convertView;
    }
}