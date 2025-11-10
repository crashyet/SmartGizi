package com.crashyet.smartstunting.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crashyet.smartstunting.R
import kotlin.math.ceil

class UserFragment : Fragment() {
    private lateinit var adapter: UserAdapter
    private lateinit var txtPage: TextView
    private var visibleColumns = mutableSetOf(
        "Nama", "NIK", "Email", "Role", "Status", "Action"
    )

    private val allColumns = listOf(
        "Foto", "ID User", "No HP", "Nama Ayah", "Nama Ibu",
        "Puskesmas", "Gender", "Tanggal Lahir", "Alamat",
        "Email Verified", "Dibuat", "Diperbarui",
        "Nama", "NIK", "Email", "Role", "Status", "Action"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val btnPrev = view.findViewById<Button>(R.id.btnPrev)
        val btnNext = view.findViewById<Button>(R.id.btnNext)
        val btnTambahKolom = view.findViewById<Button>(R.id.btnTambahKolom)
        txtPage = view.findViewById(R.id.txtPage)

        adapter = UserAdapter(generateDummyUsers(), visibleColumns) { user, action ->
            when (action) {
                "edit" -> println("Ubah data: ${user.nama}")
                "delete" -> println("Hapus data: ${user.nama}")
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        updatePageInfo()

        btnPrev.setOnClickListener {
            adapter.previousPage()
            updatePageInfo()
        }

        btnNext.setOnClickListener {
            adapter.nextPage()
            updatePageInfo()
        }

        btnTambahKolom.setOnClickListener { showColumnPopup(it) }

        return view
    }

    private fun showColumnPopup(anchor: View) {
        val popup = PopupMenu(requireContext(), anchor)
        allColumns.forEach { col ->
            popup.menu.add(col).isCheckable = true
        }

        // Set checked sesuai kolom yang tampil
        for (i in 0 until popup.menu.size()) {
            val item = popup.menu.getItem(i)
            item.isChecked = visibleColumns.contains(item.title.toString())
        }

        popup.setOnMenuItemClickListener { item ->
            val title = item.title.toString()
            if (visibleColumns.contains(title)) {
                visibleColumns.remove(title)
            } else {
                visibleColumns.add(title)
            }
            item.isChecked = !item.isChecked
            adapter.updateVisibleColumns(visibleColumns)
            true
        }
        popup.show()
    }

    private fun updatePageInfo() {
        txtPage.text = "Halaman ${adapter.currentPageIndex + 1} dari ${adapter.totalPages}"
    }

    private fun generateDummyUsers(): List<User> = List(20) {
        User(
            foto = "foto$it.png",
            idUser = "USR-${1000 + it}",
            noHp = "08123${1000 + it}",
            namaAyah = "Ayah $it",
            namaIbu = "Ibu $it",
            puskesmas = "Puskesmas Karangturi",
            gender = if (it % 2 == 0) "Laki-laki" else "Perempuan",
            tanggalLahir = "200${it % 10}-0${(it % 12) + 1}-15",
            alamat = "Jl. Mawar No. ${it + 1}",
            emailVerified = it % 2 == 0,
            createdAt = "2025-01-01",
            updatedAt = "2025-02-01",
            nama = "User $it",
            nik = "3300${it}0000000000",
            email = "user$it@mail.com",
            role = if (it % 2 == 0) "Admin" else "Petugas",
            status = it % 2 == 0
        )
    }
}