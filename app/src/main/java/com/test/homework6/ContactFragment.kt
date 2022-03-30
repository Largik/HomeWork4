//package com.test.homework6
//
//import android.annotation.SuppressLint
//import android.app.AlertDialog
//import android.content.ContentResolver
//import android.content.pm.PackageManager
//import android.database.Cursor
//import android.net.Uri
//import android.os.Bundle
//import android.provider.BaseColumns._ID
//import android.provider.ContactsContract
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.Toast
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//
//
//class ContactFragment : Fragment() {
//    private val contactList = mutableListOf<Contact>()
//
//    private val requestPermissionLauncher =
//        registerForActivityResult(ActivityResultContracts.RequestPermission())
//        { isGranted: Boolean ->
//            if (isGranted) {
//                getContacts()
//            } else {
//                Toast.makeText(requireContext(), "Отказано в доступе", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_contact, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        view.findViewById<Button>(R.id.get_contacts).setOnClickListener {
//            when {
//                ContextCompat.checkSelfPermission(
//                    requireContext(),
//                    android.Manifest.permission.READ_CONTACTS
//                ) == PackageManager.PERMISSION_GRANTED -> {
//                    getContacts()
//                }
//                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_CONTACTS) -> {
//                    val builder = AlertDialog.Builder(requireContext())
//                    builder.setTitle("Нужен доступ к контактам!")
//                        .setMessage("Ну очень нужно!")
//                        .setCancelable(true)
//                        .setPositiveButton("ОК") { dialog, _ ->
//                            dialog.cancel()
//                            requestPermissionLauncher.launch(android.Manifest.permission.READ_CONTACTS)
//                        }
//                        .setNegativeButton("Неа") { dialog, _ ->
//                            dialog.cancel()
//                        }
//                    builder.create().show()
//                }
//                else -> {
//                    requestPermissionLauncher.launch(android.Manifest.permission.READ_CONTACTS)
//                }
//            }
//        }
//    }
//
//    @SuppressLint("Range", "Recycle")
//    private fun getContacts() {
//        val uri: Uri = ContactsContract.Contacts.CONTENT_URI
//        val displayName = ContactsContract.Contacts.DISPLAY_NAME
//        val hasNumber = ContactsContract.Contacts.HAS_PHONE_NUMBER
//
//        val contentResolver: ContentResolver? = context?.contentResolver
//        val contacts: Cursor? = contentResolver?.query(uri, null, null, null, null)
//
//        if (contacts != null) {
//            while (contacts.moveToNext()) {
//                val id: String = contacts.getString(contacts.getColumnIndex(_ID))
//                val name: String = contacts.getString(contacts.getColumnIndex(displayName))
//                val hasPhoneNumber: Int =
//                    contacts.getString(contacts.getColumnIndex(hasNumber)).toInt()
//
//                if (hasPhoneNumber > 0) {
//                    val phoneCursor: Cursor? = activity?.contentResolver?.query(
//                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                        null,
//                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
//                        arrayOf(id),
//                        null
//                    )
//                    if (phoneCursor != null) {
//                        if (phoneCursor.moveToFirst()) {
//                            val phoneNumber = phoneCursor.getString(
//                                phoneCursor.getColumnIndex(
//                                    ContactsContract.CommonDataKinds.Phone.NUMBER
//                                )
//                            )
//
//                            contactList.add(Contact(name, phoneNumber))
//                            phoneCursor.close()
//                        }
//                    }
//                } else if (name.isNotEmpty()) {
//                    contactList.add(Contact(name, "number not specified"))
//                }
//            }
//            contacts.close()
//        }
//        setList(contactList)
//    }
//
//    private fun setList(contacts: List<Contact>) {
//        val recycler = view?.findViewById<RecyclerView>(R.id.contactList)
//
//        val adapter = ContactAdapter(contacts)
//        recycler?.adapter = adapter
//
//        val layoutManager = LinearLayoutManager(requireContext())
//        recycler?.layoutManager = layoutManager
//    }
//}
