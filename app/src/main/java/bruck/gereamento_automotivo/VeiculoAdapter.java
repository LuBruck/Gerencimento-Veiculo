package bruck.gereamento_automotivo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class VeiculoAdapter extends BaseAdapter {

    private Context context;

    private List<Veiculo> listaVeiculos;

    private static class VeiculoHolder {
        public TextView textViewValorApelido;
        public TextView textViewValorModelo;
        public TextView textViewValorMarca;
        public TextView textViewValorKmAtual;
        public TextView textViewValorCombustivel;
        public TextView textViewValorEmUso;
    }

    public VeiculoAdapter(Context context, List<Veiculo> listaVeiculos) {
        this.context       = context;
        this.listaVeiculos = listaVeiculos;
    }

    /* Importante não esquecer de retornar a quantidade de elementos na lista */
    @Override
    public int getCount() {
        return listaVeiculos.size();
    }

    /* Importante não esquecer de retornar o elemento que está na posição da lista */
    @Override
    public Object getItem(int position) {
        return listaVeiculos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VeiculoHolder holder;

        if (convertView == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.linha_lista_veiculo, parent, false);

            holder = new VeiculoHolder();

            holder.textViewValorApelido     = convertView.findViewById(R.id.textViewValorApelido);
            holder.textViewValorModelo      = convertView.findViewById(R.id.textViewValorModelo);
            holder.textViewValorMarca       = convertView.findViewById(R.id.textViewValorMarca);
            holder.textViewValorKmAtual     = convertView.findViewById(R.id.textViewValorKmAtual);
            holder.textViewValorCombustivel = convertView.findViewById(R.id.textViewValorCombustivel);
            holder.textViewValorEmUso       = convertView.findViewById(R.id.textViewValorEmUso);

            convertView.setTag(holder);

        }else{

            holder = (VeiculoHolder) convertView.getTag();
        }

        Veiculo veiculo = listaVeiculos.get(position);

        holder.textViewValorApelido.setText(veiculo.getApelido());
        holder.textViewValorModelo.setText(veiculo.getModelo());
        holder.textViewValorMarca.setText(veiculo.getMarca());
        holder.textViewValorKmAtual.setText(String.valueOf(veiculo.getKmAtual()));
        holder.textViewValorCombustivel.setText(veiculo.getCombustivel());

        if (veiculo.isEmUso()) {
            holder.textViewValorEmUso.setText(R.string.sim);
        } else {
            holder.textViewValorEmUso.setText(R.string.nao);
        }

        return convertView;
    }
}
