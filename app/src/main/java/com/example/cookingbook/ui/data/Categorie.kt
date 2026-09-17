package com.example.cookingbook.ui.data

/**
 * Single source of truth for recipe categories, replacing the previously duplicated (and drift-prone)
 * hardcoded lists in 'InputCategories.kt' ('categories') and 'RecipeGridScreen.kt' ('CategoryList').
 * Both lists used to be maintained by hand and could silently diverge (see [Recette.categorie]'s docs
 * for the bug this caused).
 *
 * [Recette.categorie] itself stays a plain [String] (the enum's [label]) rather than this enum type,
 * so no additional Room [androidx.room.TypeConverter] is needed and existing stored values keep
 * working as-is. Use [fromLabel] to resolve a stored label back to a [Categorie] when needed (e.g.
 * to look up a per-category icon or color).
 *
 * @property label the human-readable French label shown in the UI (dropdown, filter chips) and
 * persisted as-is in [Recette.categorie].
 */
enum class Categorie(val label: String) {
    /**
     * Pseudo-category used only as the "no filter" option in the recipe grid's category chips (see
     * 'RecipeGridScreen.kt''s 'CategoryList'). Never a valid value for [Recette.categorie] on an
     * actual recipe; excluded from [recipeCategories] for that reason.
     */
    TOUT("Tout"),
    ENTREES("Entrées"),
    PLATS("Plats"),
    DESSERTS("Desserts"),
    PAINS("Pains"),
    BOISSONS("Boissons"),
    A_TESTER("A tester"),
    PAS_CHERES("Pas chères & faciles"),
    BBQ("BBQ"),
    NOEL("Noël/Festif");

    companion object{
        /**
         * Looks up the [Categorie] whose [label] exactly matches [label], if any.
         *
         * Since [Recette.categorie] is a free-form [String] rather than this enum, a stored value
         * that no longer matches any [label] (e.g. after a category was renamed) simply returns 'null'
         * here rather than throwing.
         *
         * @param label the raw category string as stored on a [Recette].
         * @return the matching [Categorie], or 'null' if no entry has the exact label.
         */
        fun fromLabel(label: String): Categorie? = entries.find { it.label == label }

        /**
         * Every category a recipe can actually be assigned, i.e. all [entries] except [TOUT]. Used
         * to populate the add/edit form's category dropdown (see 'InputCategories.kt'), where offering
         * "Tout" as a selectable wouldn't make sense.
         */
        val recipeCategories: List<Categorie> = entries.filter { it != TOUT }
    }
}