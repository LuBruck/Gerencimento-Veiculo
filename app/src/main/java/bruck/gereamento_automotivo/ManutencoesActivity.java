package bruck.gereamento_automotivo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ManutencoesActivity extends AppCompatActivity {

    private ListView listViewManutencoes;

    private List<Manutencao> listaManutencoes;

    private ManutencaoAdapter manutencaoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manutencoes);

        listViewManutencoes = findViewById(R.id.listViewManutencoes);

        listViewManutencoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {

                Manutencao manutencao = (Manutencao) listViewManutencoes.getItemAtPosition(position);

                String[] tiposServico = getResources().getStringArray(R.array.tipos_servico);

                Toast.makeText(getApplicationContext(),
                               getString(R.string.manutencao_de_tipo) + tiposServico[manutencao.getTipoServico()] + getString(R.string.foi_clicada),
                               Toast.LENGTH_LONG).show();
            }
        });

        popularListaManutencoes();
    }

    private void popularListaManutencoes(){

        int[]    manutencoes_tipos       = getResources().getIntArray(R.array.manutencoes_tipos);
        String[] manutencoes_datas       = getResources().getStringArray(R.array.manutencoes_datas);
        int[]    manutencoes_kms         = getResources().getIntArray(R.array.manutencoes_kms);
        String[] manutencoes_custos      = getResources().getStringArray(R.array.manutencoes_custos);
        int[]    manutencoes_km_proximas = getResources().getIntArray(R.array.manutencoes_km_proximas);

        listaManutencoes = new ArrayList<>();

        Manutencao manutencao;

        for (int cont = 0; cont < manutencoes_tipos.length; cont++){

            manutencao = new Manutencao(manutencoes_tipos[cont],
                                        manutencoes_datas[cont],
                                        manutencoes_kms[cont],
                                        Double.parseDouble(manutencoes_custos[cont]),
                                        manutencoes_km_proximas[cont]);

            listaManutencoes.add(manutencao);
        }

        manutencaoAdapter = new ManutencaoAdapter(this, listaManutencoes);

        listViewManutencoes.setAdapter(manutencaoAdapter);
    }
}
