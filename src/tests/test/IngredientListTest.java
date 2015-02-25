package test;

import org.junit.Before;
import org.junit.Test;
import shawley.IngredientList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IngredientListTest {

    private IngredientList ingredientsList;

    @Before
    public void setup() {
        this.ingredientsList = new IngredientList();
    }

    @Test
    public void testListIsEmpty() {
        assertTrue(this.ingredientsList.isEmpty());
    }

    @Test
    public void testSizeIsZeroIfListIsEmpty(){
        assertEquals(0, this.ingredientsList.size());
    }

    @Test
    public void testAddingItemToListIncrementsSize() {
        this.ingredientsList.addIngredient("Ingredient");
        assertEquals(1, this.ingredientsList.size());
    }

    @Test
    public void testAddingFiveItemsToListIncrementsSizeByFive() {
        String baseName = "Ingredient";
        List<String> ingredients = new ArrayList<String>();
        for (int i = 0; i<5; i++) {
            ingredients.add(baseName+i);
        }
        Iterator<String> iter = ingredients.iterator();
        while(iter.hasNext()) {
            String ingredient = iter.next();
            this.ingredientsList.addIngredient(ingredient);
        }
        assertEquals(ingredients.size(), this.ingredientsList.size());
    }

    @Test
    public void testOutputIngredientsListWithOneItemMatchesExpectation() {
        String list = "Ingredient";
        this.ingredientsList.addIngredient(list);
        assertEquals(list, this.ingredientsList.toString());
    }

    @Test
    public void testOutputIngredientsListWithThreeItemsMatchesExpectation() {
        String list = "Ingredient0" +
                "\nIngredient1" +
                "\nIngredient2";
        String baseName = "Ingredient";
        this.ingredientsList.addIngredient(baseName+"0");
        this.ingredientsList.addIngredient(baseName+"1");
        this.ingredientsList.addIngredient(baseName+"2");
        assertEquals(list, this.ingredientsList.getShoppingList());
    }

}