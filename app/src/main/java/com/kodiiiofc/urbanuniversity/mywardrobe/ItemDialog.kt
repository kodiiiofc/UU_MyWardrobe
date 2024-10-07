package com.kodiiiofc.urbanuniversity.mywardrobe

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class ItemDialog : DialogFragment() {

    private var updatable: Updatable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        updatable = context as Updatable
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val item = requireArguments().getSerializable("item") as Cloth
        val builder = AlertDialog.Builder(requireActivity())
        val position = requireArguments().getInt("position")

        val detailsLayout = layoutInflater.inflate(R.layout.details_layout, null)

        detailsLayout.findViewById<ImageView>(R.id.details_image_iv).setImageResource(item.image)
        detailsLayout.findViewById<TextView>(R.id.details_name_tv).text = item.name
        detailsLayout.findViewById<TextView>(R.id.details_description_tv).text = item.description

        return builder.setTitle(item.name)
            .setView(detailsLayout)
            .setNegativeButton("Отмена", null)
            .setPositiveButton("Редактировать") { dialog, _ ->

                val editorDialog = AlertDialog.Builder(requireActivity())
                val editorView = layoutInflater.inflate(R.layout.editor_layout, null)
                val nameET = editorView.findViewById<EditText>(R.id.editor_name_et)
                val descriptionET = editorView.findViewById<EditText>(R.id.editor_description_et)
                nameET.setText(item.name)
                descriptionET.setText(item.description)

                editorDialog.setTitle("Редактирование")
                    .setView(editorView)
                    .setPositiveButton("Сохранить") { dialogInner, _ ->
                        val name = nameET.text.toString()
                        val description = descriptionET.text.toString()
                        val editedItem = item.copy(name = name, description = description)
                        updatable?.updateItem(editedItem, position)
                        dialogInner?.dismiss()
                    }
                    .setNegativeButton("Отмена", null)
                    .create().show()
                dialog?.dismiss()

            }

            .create()
    }

}