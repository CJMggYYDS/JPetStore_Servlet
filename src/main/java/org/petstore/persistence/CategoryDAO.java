package org.petstore.persistence;

import org.petstore.domain.Category;
import java.util.List;

public interface CategoryDAO
{
    List<Category> getCategoryList();

    Category getCategory(String categoryID);
}
