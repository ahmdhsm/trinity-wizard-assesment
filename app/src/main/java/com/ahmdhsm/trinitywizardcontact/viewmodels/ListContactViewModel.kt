package com.ahmdhsm.trinitywizardcontact.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import com.ahmdhsm.trinitywizardcontact.core.readJSONFromAssets
import com.ahmdhsm.trinitywizardcontact.models.ContactModel
import com.google.gson.Gson

@SuppressLint("StaticFieldLeak")
class ListContactViewModel(private val context: Context) : ViewModel() {
    var listContact: MutableList<ContactModel> = mutableListOf()

    fun getContactFromAsset() {
        listContact.clear()
        val jsonString = readJSONFromAssets(context, "contact.json")
        val list = Gson().fromJson(jsonString, Array<ContactModel>::class.java)
        listContact.addAll(list)

    }

    fun addContact(contact: ContactModel) {
        listContact.add(contact)
    }

    fun getContactById(contactId: String): ContactModel? {
        for (contact in listContact) {
            if (contact.id == contactId) {
                return contact
            }
        }
        return null
    }

    fun updateContact(contactId: String, contactModel: ContactModel) {
        var currentContact: ContactModel? = null
        var contactIndex: Int? = null

        var count = 0
        for (contact in listContact) {
            if (contact.id == contactId) {
                currentContact = contact
                contactIndex = count
                break
            }
            count += 1
        }
        if (currentContact != null) {
            currentContact = contactModel.copy(id = currentContact.id)
        }

        if (contactIndex != null && currentContact != null) {
            listContact.add(contactIndex, currentContact)

        }
    }
}