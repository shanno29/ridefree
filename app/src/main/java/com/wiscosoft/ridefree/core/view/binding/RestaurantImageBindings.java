package com.wiscosoft.ridefree.core.view.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

//import com.squareup.picasso.Picasso;

public class RestaurantImageBindings {

  @BindingAdapter(value = {"imageUrl"})
  public static void loadImage(ImageView view, String imageUrl) {
    //Picasso.with(view.getContext()).load(imageUrl).fit().into(view);
  }
}