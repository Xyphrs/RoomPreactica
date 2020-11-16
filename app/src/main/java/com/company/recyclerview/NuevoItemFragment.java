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

import com.company.recyclerview.databinding.FragmentNuevoItemBinding;

public class NuevoItemFragment extends Fragment {
        private FragmentNuevoItemBinding binding;

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return (binding = FragmentNuevoItemBinding.inflate(inflater, container, false)).getRoot();
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            ItemViewModel itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
            NavController navController = Navigation.findNavController(view);

            binding.crear.setOnClickListener(v -> {
                String nombre = binding.nombre.getText().toString();
                String descripcion = binding.descripcion.getText().toString();

                itemViewModel.insertar(new Items(nombre, descripcion));

                navController.popBackStack();
            });
        }
}
