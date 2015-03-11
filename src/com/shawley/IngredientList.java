package shawley;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IngredientList {

    private final List<String> ingredientList = new ArrayList<>();

    public boolean isEmpty() {
        return this.ingredientList.size() == 0;
    }

    public int size() {
        return this.ingredientList.size();
    }

    public void addIngredient(String ingredient) {
        this.ingredientList.add(ingredient);
    }

    private Iterator<String> iterator() {
        return this.ingredientList.iterator();
    }



    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Iterator<String> iter = this.iterator();
        while(iter.hasNext()) {
            String ingredient = iter.next();
            result.append(ingredient);
            result.append("\n");
        }
        result.deleteCharAt(result.length()-1);
        return result.toString();
    }

    public String getShoppingList() {
        return this.toString();
    }
}
