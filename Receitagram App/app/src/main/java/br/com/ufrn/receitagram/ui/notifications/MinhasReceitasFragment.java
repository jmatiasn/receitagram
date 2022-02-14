package br.com.ufrn.receitagram.ui.notifications;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.ufrn.receitagram.AddReceitaActivity;
import br.com.ufrn.receitagram.adapters.AdapterReceita;
import br.com.ufrn.receitagram.dao.ReceitaDAO;
import br.com.ufrn.receitagram.databinding.FragmentMinhasReceitasBinding;
import br.com.ufrn.receitagram.modelo.Receita;

public class MinhasReceitasFragment extends Fragment {

    private FragmentMinhasReceitasBinding binding;
    FloatingActionButton fabAdd;
    RecyclerView rvMinhasReceitas;

    AdapterReceita adapterReceita;

    List<Receita> minhasReceitas = new ArrayList<Receita>();

    Receita receitaSelecionada = new Receita();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //MinhasReceitasViewModel minhasReceitasViewModel =
        //        new ViewModelProvider(this).get(MinhasReceitasViewModel.class);

        binding = FragmentMinhasReceitasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fabAdd = binding.fabAdd;
        rvMinhasReceitas = binding.rvMinhasReceitas;

        /*minhasReceitas = obterReceitas(minhasReceitas);
        adapterReceita = new AdapterReceita(minhasReceitas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvMinhasReceitas.setLayoutManager(layoutManager);
        rvMinhasReceitas.setHasFixedSize(true);
        rvMinhasReceitas.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        rvMinhasReceitas.setAdapter(adapterReceita);*/

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), AddReceitaActivity.class);
                startActivity(it);
            }
        });

        //final TextView textView = binding.textNotifications;
        //minhasReceitasViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void carregarReceitas(){
        ReceitaDAO receitaDAO = new ReceitaDAO(getActivity());
        minhasReceitas = receitaDAO.listar();
        //Toast.makeText(getActivity(), "Qtd: " + String.valueOf(minhasReceitas.size()), Toast.LENGTH_SHORT).show();

        adapterReceita = new AdapterReceita(minhasReceitas);
        adapterReceita.implementaAoClicarNoItem(new AdapterReceita.AoClicarNoItem() {
            @Override
            public void clicouNaReceita(int pos) {
                //Editar
                Receita receita = new Receita();
                receita = minhasReceitas.get(pos);

                Intent it = new Intent(getActivity(), AddReceitaActivity.class);
                it.putExtra("receita",receita);
                startActivity(it);
            }

            @Override
            public void pressionouReceita(int pos) {
                receitaSelecionada = minhasReceitas.get(pos);

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

                dialog.setTitle("Confirmar Exclusão");

                dialog.setMessage("Deseja excluir a receita: "+ receitaSelecionada.getNome()+ " ?");

                dialog.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ReceitaDAO receitaDAO = new ReceitaDAO(getActivity());
                        if(receitaDAO.deletar(receitaSelecionada)){
                            Toast.makeText(getActivity(), "Receita foi removida", Toast.LENGTH_SHORT).show();
                            carregarReceitas();
                        } else {
                            Toast.makeText(getActivity(), "Erro ao deletar receita", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.setNegativeButton("Não",null);

                dialog.create();
                dialog.show();

            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvMinhasReceitas.setLayoutManager(layoutManager);
        rvMinhasReceitas.setHasFixedSize(true);
        rvMinhasReceitas.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        rvMinhasReceitas.setAdapter(adapterReceita);

    }

    @Override
    public void onResume() {
        this.carregarReceitas();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private List<Receita> obterReceitas(List<Receita> mReceitas) {
        //RECEITA 1 ---------------------------------------------------------
        String ingredientes = "1 lata de leite condensado, "
                + "1 lata de creme de leite, "
                + "1 xícara de chocolate em pó, "
                + "1 colher (sopa) de margarina, "
                + "3 ovos inteiros, "
                + "chocolate granulado para enfeitar ";
        String modoDePreparo = "Coloque todos os ingredientes no liquidificador e bata bem até dobrar de volume., "
                + "Despeje a massa em uma forma redonda com furo central untada e leve ao micro-ondas por 10 minutos., "
                + "Ao retirar do micro-ondas, enfeite com chocolate granulado e bom apetite. ";
        Receita novaReceita = new Receita(1L,
                "BRIGADEIRÃO DE MICRO-ONDAS",
                8,
                ingredientes,
                modoDePreparo,
                "TUDOGOSTOSO",
                new Date());
        mReceitas.add(novaReceita);
        return mReceitas;
    }

}