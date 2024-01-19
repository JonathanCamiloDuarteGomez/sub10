package com.example.sub10.vistas.HomeCliente;

import android.os.Bundle;

import androidx.navigation.NavDirections;
import com.example.sub10.R;

public class SearchFragmentDirections {

    SearchFragment sf = new SearchFragment();

    private static class ActionSearchFragmentToSearchResultFragment implements NavDirections {
        private final String nameQuery;

        public ActionSearchFragmentToSearchResultFragment(String nameQuery) {
            this.nameQuery = nameQuery;
        }

        @Override
        public int getActionId() {
            return R.id.action_searchFragment_to_searchResultFragment;
        }

        @Override
        public Bundle getArguments() {
            Bundle result = new Bundle();
            result.putString("nameQuery", this.nameQuery);
            return result;
        }
    }

    public static NavDirections actionSearchFragmentToSearchResultFragment(String nameQuery) {
        return new ActionSearchFragmentToSearchResultFragment(nameQuery);
    }
}