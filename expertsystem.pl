question(do_you_prefer_dishes_with_melted_cheese).
question(do_you_enjoy_raw_fish_or_seafood).
question(are_you_a_fan_of_sandwiches_with_various_fillings).
question(do_you_like_pasta_dishes_with_different_types_of_sauces).
question(do_you_prefer_light_meals_with_fresh_vegetables).
question(do_you_enjoy_spicy_dishes).
question(are_you_a_meat_lover).
question(do_you_enjoy_noodle_based_dishes).
question(are_you_a_fan_of_grilled_or_pan_seared_meat).
question(do_you_prefer_your_meals_between_two_slices_of_bread).
question(do_you_like_dishes_with_a_blend_of_spices_and_herbs).
question(do_you_enjoy_dishes_with_various_toppings).


food(pizza, [do_you_prefer_dishes_with_melted_cheese,
			do_you_enjoy_dishes_with_various_toppings,
			do_you_enjoy_spicy_dishes
			]).
food(sushi, [do_you_enjoy_raw_fish_or_seafood,
			do_you_like_dishes_with_a_blend_of_spices_and_herbs,
			do_you_enjoy_spicy_dishes
			]).
food(pasta, [do_you_enjoy_noodle_based_dishes,
			do_you_enjoy_spicy_dishes,
			do_you_like_pasta_dishes_with_different_types_of_sauces
			]).
food(salad, [do_you_prefer_light_meals_with_fresh_vegetables,
			do_you_like_dishes_with_a_blend_of_spices_and_herbs,
			do_you_enjoy_dishes_with_various_toppings
			]).
food(burger, [are_you_a_meat_lover,
			do_you_prefer_your_meals_between_two_slices_of_bread,
			do_you_enjoy_spicy_dishes
			]).
food(steak, [are_you_a_meat_lover,
			do_you_enjoy_spicy_dishes,
			are_you_a_fan_of_grilled_or_pan_seared_meat
			]).
food(tacos, [do_you_prefer_your_meals_between_two_slices_of_bread,
			do_you_enjoy_spicy_dishes,
			do_you_enjoy_dishes_with_various_toppings
			]).

			
			
matches_food(Food, UserPreference) :-
    food(Food, RequiredPreference),
    permutation(RequiredPreference, UserPreference).
	
predict(UserPreference, Food) :-
    findall(Food, matches_food(Food, UserPreference), Food).