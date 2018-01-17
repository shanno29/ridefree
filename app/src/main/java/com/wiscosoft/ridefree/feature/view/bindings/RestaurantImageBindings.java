package com.wiscosoft.ridefree.feature.view.bindings;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

public class RestaurantImageBindings {

  @BindingAdapter(value = {"imageUrl"})
  public static void loadImage(ImageView view, String imageUrl) {
    //Picasso.with(view.getContext()).load(imageUrl).fit().into(view);
  }
}