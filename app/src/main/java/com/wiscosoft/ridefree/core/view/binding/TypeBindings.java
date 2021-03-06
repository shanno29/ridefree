package com.wiscosoft.ridefree.core.view.binding;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.wiscosoft.ridefree.R;

import br.com.ilhasoft.support.validation.rule.TypeRule;
import br.com.ilhasoft.support.validation.util.EditTextHandler;
import br.com.ilhasoft.support.validation.util.ErrorMessageHelper;
import br.com.ilhasoft.support.validation.util.ViewTagHelper;

public class TypeBindings {

  @BindingAdapter(value = {"validateType", "validateTypeMessage", "validateTypeAutoDismiss"}, requireAll = false)
  public static void bindingTypeValidation(TextView view, String fieldTypeText, String errorMessage, boolean autoDismiss) {
    if (autoDismiss) {EditTextHandler.disableErrorOnChanged(view); }
    TypeRule.FieldType fieldType = getFieldTypeByText(fieldTypeText);
    try {
      String handledErrorMessage = ErrorMessageHelper.getStringOrDefault(view, errorMessage, fieldType.errorMessageId);
      ViewTagHelper.appendValue(R.id.validator_rule, view, fieldType.instantiate(view, handledErrorMessage));
    } catch (Exception ignored) {
    }
  }

  @NonNull
  private static TypeRule.FieldType getFieldTypeByText(String fieldTypeText) {
    TypeRule.FieldType fieldType = TypeRule.FieldType.None;
    for (TypeRule.FieldType type : TypeRule.FieldType.values()) {
      if (type.toString().equalsIgnoreCase(fieldTypeText)) {
        fieldType = type;
        break;
      }
    }
    return fieldType;
  }
}