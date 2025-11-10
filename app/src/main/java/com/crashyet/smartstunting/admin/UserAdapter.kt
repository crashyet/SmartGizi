package com.crashyet.smartstunting.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crashyet.smartstunting.R

class UserAdapter(
    private val fullList: List<User>,
    private var visibleColumns: Set<String>,
    private val onActionClick: (User, String) -> Unit
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var currentPage = 0
    private val itemsPerPage = 10

    val currentPageIndex: Int
        get() = currentPage

    val totalPages: Int
        get() = (fullList.size + itemsPerPage - 1) / itemsPerPage

    fun updateVisibleColumns(columns: Set<String>) {
        visibleColumns = columns
        notifyDataSetChanged()
    }

    fun nextPage() {
        if (currentPage < totalPages - 1) {
            currentPage++
            notifyDataSetChanged()
        }
    }

    fun previousPage() {
        if (currentPage > 0) {
            currentPage--
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        val start = currentPage * itemsPerPage
        val end = minOf(start + itemsPerPage, fullList.size)
        return end - start
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val start = currentPage * itemsPerPage
        val user = fullList[start + position]
        holder.bind(user, visibleColumns, onActionClick)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtNama = itemView.findViewById<TextView>(R.id.txtNama)
        private val txtNik = itemView.findViewById<TextView>(R.id.txtNik)
        private val txtEmail = itemView.findViewById<TextView>(R.id.txtEmail)
        private val txtRole = itemView.findViewById<TextView>(R.id.txtRole)
        private val txtStatus = itemView.findViewById<TextView>(R.id.txtStatus)
        private val imgFoto = itemView.findViewById<ImageView>(R.id.imgFoto)
        private val btnEdit = itemView.findViewById<Button>(R.id.btnEdit)
        private val btnDelete = itemView.findViewById<Button>(R.id.btnDelete)

        fun bind(user: User, visibleColumns: Set<String>, onActionClick: (User, String) -> Unit) {
            txtNama.text = user.nama
            txtNik.text = user.nik
            txtEmail.text = user.email
            txtRole.text = user.role
            txtStatus.text = if (user.status) "Aktif" else "Tidak Aktif"

            // Set visibilitas sesuai kolom yang dipilih
            txtNama.visibility = if ("Nama" in visibleColumns) View.VISIBLE else View.GONE
            txtNik.visibility = if ("NIK" in visibleColumns) View.VISIBLE else View.GONE
            txtEmail.visibility = if ("Email" in visibleColumns) View.VISIBLE else View.GONE
            txtRole.visibility = if ("Role" in visibleColumns) View.VISIBLE else View.GONE
            txtStatus.visibility = if ("Status" in visibleColumns) View.VISIBLE else View.GONE
            imgFoto.visibility = if ("Foto" in visibleColumns) View.VISIBLE else View.GONE

            if ("Action" in visibleColumns) {
                btnEdit.visibility = View.VISIBLE
                btnDelete.visibility = View.VISIBLE
            } else {
                btnEdit.visibility = View.GONE
                btnDelete.visibility = View.GONE
            }

            btnEdit.setOnClickListener { onActionClick(user, "edit") }
            btnDelete.setOnClickListener { onActionClick(user, "delete") }
        }
    }
}
