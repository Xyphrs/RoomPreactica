package com.company.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.company.recyclerview.databinding.FragmentRecyclerElementosBinding;
import com.company.recyclerview.databinding.ViewholderElementoBinding;

import java.util.List;


public class RecyclerItemFragment extends Fragment {

    private FragmentRecyclerElementosBinding binding;
    private ItemViewModel itemViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerElementosBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        navController = Navigation.findNavController(view);

        binding.IrANuevoItem.setOnClickListener(v -> navController.navigate(R.id.action_nuevoItemFragment));


        ElementosAdapter elementosAdapter = new ElementosAdapter();
        binding.recycler.setAdapter(elementosAdapter);

        binding.recycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        itemViewModel.obtener().observe(getViewLifecycleOwner(), elementosAdapter::establishList);
    }
        class ElementosAdapter extends RecyclerView.Adapter<ElementoViewHolder> {
            List<Items> itemsList;

            @NonNull
            @Override
            public ElementoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ElementoViewHolder(ViewholderElementoBinding.inflate(getLayoutInflater(), parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull ElementoViewHolder holder, int position) {
                Items items = itemsList.get(position);

                holder.binding.nombre.setText(items.nombre);

                holder.itemView.setOnClickListener(v -> {
                    itemViewModel.establecerElementoSeleccionado(items);
                    navController.navigate(R.id.action_recyclerElementosFragment_to_mostrarElementoFragment);
                });
            }

            @Override
            public int getItemCount() {
                return itemsList !=null ? itemsList.size() : 0;
            }

            public void establishList(List<Items> items){
                this.itemsList = items;
                notifyDataSetChanged();
            }
        }

    static class ElementoViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderElementoBinding binding;

        public ElementoViewHolder(ViewholderElementoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}