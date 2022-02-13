package br.com.ufrn.receitagram.adapters;
import br.com.ufrn.receitagram.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import br.com.ufrn.receitagram.modelo.Receita;

public class AdapterReceita extends RecyclerView.Adapter<AdapterReceita.MinhaViewHolder> {

    private List<Receita> mReceitas;

    public AdapterReceita(List<Receita> lista){
        mReceitas = lista;
    }

    @NonNull
    @Override
    public MinhaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elemento_lista,parent,false);

        return new MinhaViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MinhaViewHolder holder, int position) {

        Receita receita = mReceitas.get(position);
        holder.tvNomeReceita.setText(receita.getNome());
        holder.tvRendimentoEmPorcoes.setText("Porções: "
                + String.valueOf(receita.getRendimentoEmPorcoes()));
        holder.tvAutor.setText(receita.getAutorNomeESobrenome());
    }

    @Override
    public int getItemCount() {
        return mReceitas.size();
    }

    public class MinhaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        CardView cvReceitaResumo;
        TextView tvNomeReceita;
        TextView tvRendimentoEmPorcoes;
        TextView tvAutor;

        public MinhaViewHolder(View itemView){
            super(itemView);

            cvReceitaResumo = itemView.findViewById(R.id.cvReceitaResumo);
            tvNomeReceita = itemView.findViewById(R.id.tvNomeReceita);
            tvRendimentoEmPorcoes = itemView.findViewById(R.id.tvRendimentoEmPorcoes);
            tvAutor = itemView.findViewById(R.id.tvAutor);

            cvReceitaResumo.setOnClickListener(this);
            cvReceitaResumo.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.clicouNaReceita(getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            listener.pressionouReceita(getLayoutPosition());
            return true;
        }
    }

    public interface AoClicarNoItem{
        public void clicouNaReceita(int pos);
        public void pressionouReceita(int pos);
    }

    public AoClicarNoItem listener;

    public void implementaAoClicarNoItem(AoClicarNoItem listener){
        this.listener = listener;
    }
}
