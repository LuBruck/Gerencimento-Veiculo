package bruck.gereamento_automotivo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ManutencaoAdapter extends BaseAdapter {

    private Context context;

    private List<Manutencao> listaManutencoes;

    private String[] tiposServico;

    private static class ManutencaoHolder {
        public TextView textViewValorServico;
        public TextView textViewValorData;
        public TextView textViewValorKm;
        public TextView textViewValorCusto;
        public TextView textViewValorProximaTroca;
    }

    public ManutencaoAdapter(Context context, List<Manutencao> listaManutencoes) {
        this.context          = context;
        this.listaManutencoes = listaManutencoes;

        tiposServico = context.getResources().getStringArray(R.array.tipos_servico);
    }

    /* Importante não esquecer de retornar a quantidade de elementos na lista */
    @Override
    public int getCount() {
        return listaManutencoes.size();
    }

    /* Importante não esquecer de retornar o elemento que está na posição da lista */
    @Override
    public Object getItem(int position) {
        return listaManutencoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ManutencaoHolder holder;

        if (convertView == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.linha_lista_manutencao, parent, false);

            holder = new ManutencaoHolder();

            holder.textViewValorServico       = convertView.findViewById(R.id.textViewValorServico);
            holder.textViewValorData          = convertView.findViewById(R.id.textViewValorData);
            holder.textViewValorKm            = convertView.findViewById(R.id.textViewValorKm);
            holder.textViewValorCusto         = convertView.findViewById(R.id.textViewValorCusto);
            holder.textViewValorProximaTroca  = convertView.findViewById(R.id.textViewValorProximaTroca);

            convertView.setTag(holder);

        }else{

            holder = (ManutencaoHolder) convertView.getTag();
        }

        Manutencao manutencao = listaManutencoes.get(position);

        holder.textViewValorServico.setText(tiposServico[manutencao.getTipoServico()]);
        holder.textViewValorData.setText(manutencao.getData());
        holder.textViewValorKm.setText(String.valueOf(manutencao.getKm()));
        holder.textViewValorCusto.setText(String.valueOf(manutencao.getCusto()));
        holder.textViewValorProximaTroca.setText(String.valueOf(manutencao.getKmProximaTroca()));

        return convertView;
    }
}
