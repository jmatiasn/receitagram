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

        mReceitas = obterReceitas(mReceitas);
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

    private List<Receita> obterReceitas(List<Receita> mReceitas) {
        //RECEITA 1 ---------------------------------------------------------
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
                "BRIGADEIRÃO DE MICRO-ONDAS",
                8,
                ingredientes,
                modoDePreparo,
                "TUDOGOSTOSO",
                new Date());
        mReceitas.add(novaReceita);

        //RECEITA 2 ---------------------------------------------------------
        ingredientes = new String[] { "1 couve-flor média",
                "1 copo de requeijão",
                "2 ovos",
                "50 g de queijo ralado",
                "200 g de mussarela",
                "cheiro-verde",
                "1 tomate picadinho",
                "margarina para untar"
        };
        modoDePreparo = new String[] { "Cozinhar a couve-flor com sal até ficar bem macia.",
                "Escorra e coloque em forma untada com margarina.",
                "Acrescente o requeijão (colheradas espalhadas).",
                "Bata os ovos e junte 25 g de queijo ralado, cheiro verde e o tomate.",
                "Jogue os ovos batidos por cima da couve-flor.",
                "Cubra com a mussarela, polvilhe com o restante do queijo ralado.",
                "Leve ao forno para gratinar.",
        };
        novaReceita = new Receita(2L,
                "COUVE-FLOR GRATINADA",
                8,
                ingredientes,
                modoDePreparo,
                "ROZANGELA CHAVES",
                new Date());
        mReceitas.add(novaReceita);

        //RECEITA 3 ---------------------------------------------------------
        ingredientes = new String[] { "2 latas de atum em óleo",
                "1 xícara de feijão fradinho cozido",
                "1/2 cebola roxa",
                "2 tomates",
                "1/2 lata de milho",
                "3 colheres (sopa) de cheiro-verde",
                "1/2 limão",
                "Sal a gosto",
                "Pimenta-do-reino a gosto",
                "1/2 xícara de manjericão",
                "3 colheres (sopa) de azeite",
        };
        modoDePreparo = new String[] { "Em uma tigela, misture todos os ingredientes.",
                "Tempere com sal, pimenta-do-reino, manjericão e azeite.",
        };
        novaReceita = new Receita(3L,
                "SALADA DE ATUM COM FEIJÃO FRADINHO",
                4,
                ingredientes,
                modoDePreparo,
                "ROZANGELA CHAVES",
                new Date());
        mReceitas.add(novaReceita);

        //RECEITA 4 ---------------------------------------------------------
        ingredientes = new String[] { "2 copos (americano) de leite",
                "2 colheres (sopa) de margarina",
                "1 cubo de caldo de galinha",
                "2 copos (americano) de farinha de trigo",
                "1/2 kg de mandioca cozida",
        };
        modoDePreparo = new String[] { "Leve ao fogo: leite, margarina, caldo.",
                "Quando ferver junte toda farinha.",
                "Mexa até cozinhar bem.",
                "Desligue.",
                "Misture a mandioca e sove para agregar.",
                "Modele as coxinhas colocando o recheio.",
                "Passe no ovo, farinha de rosca e frite.",
        };
        novaReceita = new Receita(4L,
                "COXINHA DE MANDIOCA",
                20,
                ingredientes,
                modoDePreparo,
                "MARIA F. N. VECHI",
                new Date());
        mReceitas.add(novaReceita);

        //RECEITA 5 ---------------------------------------------------------
        ingredientes = new String[] { "1/2 kg de carne moída",
                "1 pacote de sopa de cebola",
                "presunto fatiado",
                "queijo fatiado",
                "tempero verde",
                "sal a gosto",
        };
        modoDePreparo = new String[] { "Tempere a carne moída com a sopa de cebola, o tempero verde e o sal.",
                "Coloque a carne temperada sobre uma folha de papel laminado ou papel manteiga e abra a massa com um rolo, na espessura de 1 cm, mais ou menos.",
                "Forre a carne com o presunto e o queijo, pode-se colocar também milho verde, ervilha e requeijão.",
                "Enrole a carne, com ajuda da folha de papel laminado ou manteiga, em forma de rocambole.",
                "Leve ao forno, em temperatura alta, por mais ou menos 30 minutos, ou no microondas por 15 minutos.",
                "Bom apetite!",
        };
        novaReceita = new Receita(5L,
                "ROCAMBOLE DE CARNE MOÍDA MARAVILHOSO",
                8,
                ingredientes,
                modoDePreparo,
                "LUCIERE",
                new Date());
        mReceitas.add(novaReceita);

        return(mReceitas);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}