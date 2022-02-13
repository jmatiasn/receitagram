package br.com.ufrn.receitagram.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.com.ufrn.receitagram.adapters.AdapterReceita;
import br.com.ufrn.receitagram.databinding.FragmentHomeBinding;
import br.com.ufrn.receitagram.modelo.Receita;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    RecyclerView rvReceitas;
    AdapterReceita adapterReceita;
    List<Receita> mReceitas = new ArrayList<Receita>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //HomeViewModel homeViewModel =
        //        new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        String[] ingredientes = { "1 lata de leite condensado",
                "1 lata de creme de leite",
                "1 xícara de chocolate em pó",
                "1 colher (sopa) de margarina",
                "3 ovos inteiros",
                "chocolate granulado para enfeitar",
        } ;
        String[] modoDePreparo = { "Coloque todos os ingredientes no liquidificador e bata bem até dobrar de volume.",
                "Despeje a massa em uma forma redonda com furo central untada e leve ao micro-ondas por 10 minutos.",
                "Ao retirar do micro-ondas, enfeite com chocolate granulado e bom apetite.",
        } ;
        Receita novaReceita = new Receita(1L,
                1L,
                "BRIGADEIRÃO DE MICRO-ONDAS",
                8,
                ingredientes,
                modoDePreparo,
                new Date());
        mReceitas.add(novaReceita);
        mReceitas.add(novaReceita);
        mReceitas.add(novaReceita);
        mReceitas.add(novaReceita);
        mReceitas.add(novaReceita);

        adapterReceita = new AdapterReceita(mReceitas);


        //configuração recycler view
        rvReceitas = binding.rvReceitas;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvReceitas.setLayoutManager(layoutManager);
        rvReceitas.setHasFixedSize(true);
        rvReceitas.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        rvReceitas.setAdapter(adapterReceita);

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}