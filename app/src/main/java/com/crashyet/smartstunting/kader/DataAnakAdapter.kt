package com.crashyet.smartstunting.kader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.crashyet.smartstunting.R

class DataAnakAdapter(
    private val list: ArrayList<AnakModel>,
    private val onLihat: (AnakModel) -> Unit,
    private val onUbah: (AnakModel) -> Unit,
    private val onHapus: (AnakModel) -> Unit
) : RecyclerView.Adapter<DataAnakAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val headerLayout: LinearLayout = v.findViewById(R.id.headerLayoutDataAnak)

        // Header
        val tvNamaBalita: TextView = v.findViewById(R.id.tvNamaBalita)
        val tvUmurBalita: TextView = v.findViewById(R.id.tvUmurBalita)
        val tvJnsKelamin: TextView = v.findViewById(R.id.tvJnsKelamin)

        // Detail
        val detailLayout: ConstraintLayout = v.findViewById(R.id.detailLayoutResiko)
        val tvNIK: TextView = v.findViewById(R.id.tvNIK)
        val tvNamaAyah: TextView = v.findViewById(R.id.tvNamaAyah)
        val tvPosyandu: TextView = v.findViewById(R.id.tvPosyandu)
        val tvStatus: TextView = v.findViewById(R.id.tvStatus)

        // Buttons
        val btnLihat: Button = v.findViewById(R.id.btnLihat)
        val btnUbah: Button = v.findViewById(R.id.btnUbah)
        val btnHapus: Button = v.findViewById(R.id.btnHapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_data_anak, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        // Bind header
        holder.tvNamaBalita.text = item.nama
        holder.tvUmurBalita.text = item.umur
        holder.tvJnsKelamin.text = item.jk

        // Bind detail
        holder.tvNIK.text = item.nik
        holder.tvNamaAyah.text = item.namaAyah
        holder.tvPosyandu.text = item.posyandu
        holder.tvStatus.text = item.status

        // Expand/Collapse
        holder.detailLayout.visibility = if (item.expanded) View.VISIBLE else View.GONE

        holder.headerLayout.setOnClickListener {
            item.expanded = !item.expanded
            notifyItemChanged(position)
        }

        // Actions
        holder.btnLihat.setOnClickListener { onLihat(item) }
        holder.btnUbah.setOnClickListener { onUbah(item) }
        holder.btnHapus.setOnClickListener { onHapus(item) }
    }
}
