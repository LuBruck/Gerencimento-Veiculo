package bruck.gereamento_automotivo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import java.util.ArrayList;
import java.util.List;

public class VeiculosActivity extends AppCompatActivity {

    private ListView listViewVeiculos;

    private List<Veiculo> listaVeiculos;

    private VeiculoAdapter veiculoAdapter;

    private int posicaoSelecionada = -1;
    private ActionMode actionMode;
    private View viewSelecionada;
    private Drawable backgroundDrawable;

    private ActionMode.Callback actionCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.veiculos_item_selecionado, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            int id = item.getItemId();

            if (id == R.id.menuItemEditar) {
                editarVeiculo();
                return true;
            } else if (id == R.id.menuItemExcluir) {
                excluirVeiculo();
                mode.finish();
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            if (viewSelecionada != null) {
                viewSelecionada.setBackground(backgroundDrawable);
            }

            actionMode = null;
            viewSelecionada = null;
            backgroundDrawable = null;

            listViewVeiculos.setEnabled(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiculos);

        setTitle(R.string.titulo_veiculos);

        listViewVeiculos = findViewById(R.id.listViewVeiculos);

        listaVeiculos = new ArrayList<>();

        veiculoAdapter = new VeiculoAdapter(this, listaVeiculos);

        listViewVeiculos.setAdapter(veiculoAdapter);

        listViewVeiculos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if (actionMode != null) {
                    return false;
                }

                posicaoSelecionada = position;
                viewSelecionada = view;
                backgroundDrawable = view.getBackground();

                view.setBackgroundColor(Color.LTGRAY);

                listViewVeiculos.setEnabled(false);

                actionMode = startSupportActionMode(actionCallback);

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.veiculos_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menuItemAdicionar) {
            abrirNovoVeiculo();
            return true;
        } else if (id == R.id.menuItemSobre) {
            abrirSobre();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void abrirSobre(){

        Intent intentAbertura = new Intent(this, SobreActivity.class);

        startActivity(intentAbertura);
    }

    ActivityResultLauncher<Intent> launcherNovoVeiculo = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),

            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == VeiculosActivity.RESULT_OK){

                        Intent intent = result.getData();

                        if (intent == null){
                            return;
                        }

                        Bundle bundle = intent.getExtras();

                        if (bundle != null){

                            String apelido      = bundle.getString(CadastroVeiculoActivity.KEY_APELIDO);
                            String modelo       = bundle.getString(CadastroVeiculoActivity.KEY_MODELO);
                            int kmAtual         = bundle.getInt(CadastroVeiculoActivity.KEY_KM_ATUAL);
                            String marca        = bundle.getString(CadastroVeiculoActivity.KEY_MARCA);
                            String combustivel  = bundle.getString(CadastroVeiculoActivity.KEY_COMBUSTIVEL);
                            boolean emUso       = bundle.getBoolean(CadastroVeiculoActivity.KEY_EM_USO);

                            Veiculo veiculo = new Veiculo(apelido, modelo, kmAtual, marca, combustivel, emUso);

                            listaVeiculos.add(veiculo);

                            veiculoAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });

    private void abrirNovoVeiculo(){

        Intent intentAbertura = new Intent(this, CadastroVeiculoActivity.class);

        intentAbertura.putExtra(CadastroVeiculoActivity.KEY_MODO, CadastroVeiculoActivity.MODO_NOVO);

        launcherNovoVeiculo.launch(intentAbertura);
    }

    private void excluirVeiculo(){

        listaVeiculos.remove(posicaoSelecionada);

        veiculoAdapter.notifyDataSetChanged();
    }

    private void editarVeiculo(){

        Veiculo veiculo = listaVeiculos.get(posicaoSelecionada);

        Intent intentAbertura = new Intent(this, CadastroVeiculoActivity.class);

        intentAbertura.putExtra(CadastroVeiculoActivity.KEY_MODO, CadastroVeiculoActivity.MODO_EDITAR);
        intentAbertura.putExtra(CadastroVeiculoActivity.KEY_APELIDO, veiculo.getApelido());
        intentAbertura.putExtra(CadastroVeiculoActivity.KEY_MODELO, veiculo.getModelo());
        intentAbertura.putExtra(CadastroVeiculoActivity.KEY_KM_ATUAL, veiculo.getKmAtual());
        intentAbertura.putExtra(CadastroVeiculoActivity.KEY_MARCA, veiculo.getMarca());
        intentAbertura.putExtra(CadastroVeiculoActivity.KEY_COMBUSTIVEL, veiculo.getCombustivel());
        intentAbertura.putExtra(CadastroVeiculoActivity.KEY_EM_USO, veiculo.isEmUso());

        launcherEditarVeiculo.launch(intentAbertura);
    }

    ActivityResultLauncher<Intent> launcherEditarVeiculo = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),

            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == VeiculosActivity.RESULT_OK){

                        Intent intent = result.getData();

                        if (intent != null){

                            Bundle bundle = intent.getExtras();

                            if (bundle != null){

                                Veiculo veiculo = listaVeiculos.get(posicaoSelecionada);

                                veiculo.setApelido(bundle.getString(CadastroVeiculoActivity.KEY_APELIDO));
                                veiculo.setModelo(bundle.getString(CadastroVeiculoActivity.KEY_MODELO));
                                veiculo.setKmAtual(bundle.getInt(CadastroVeiculoActivity.KEY_KM_ATUAL));
                                veiculo.setMarca(bundle.getString(CadastroVeiculoActivity.KEY_MARCA));
                                veiculo.setCombustivel(bundle.getString(CadastroVeiculoActivity.KEY_COMBUSTIVEL));
                                veiculo.setEmUso(bundle.getBoolean(CadastroVeiculoActivity.KEY_EM_USO));

                                veiculoAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    posicaoSelecionada = -1;

                    if (actionMode != null){
                        actionMode.finish();
                    }
                }
            });
}
