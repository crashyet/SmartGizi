package com.crashyet.smartstunting.kader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.crashyet.smartstunting.R

class BalitaAdapter(
    private val list: MutableList<Balita>
) : RecyclerView.Adapter<BalitaAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headerLayout: View = view.findViewById(R.id.headerLayout)
        val detailLayout: View = view.findViewById(R.id.detailLayout)
        val tvNama: TextView = view.findViewById(R.id.tvNama)
        val tvTanggal: TextView = view.findViewById(R.id.tvTanggal)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val tvBB: TextView? = view.findViewById(R.id.tvBB)
        val tvTB: TextView? = view.findViewById(R.id.tvTB)
        val tvPetugas: TextView? = view.findViewById(R.id.tvPetugas)
        val tvPosisiMenimbang: TextView? = view.findViewById(R.id.tvPosisiMenimbang)
        val tvUmurBalita: TextView? = view.findViewById(R.id.tvUmurBalita)
        val btnEdit: Button? = view.findViewById(R.id.btnEdit)
        val btnGrafik: Button? = view.findViewById(R.id.btnGrafik)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_balita, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val balita = list[position]

        holder.tvNama.text = balita.nama
        holder.tvTanggal.text = "${balita.tanggalLahir} • ${balita.jenisKelamin}"
        holder.tvStatus.text = balita.status
        holder.tvBB?.text = balita.beratBadan
        holder.tvTB?.text = balita.tinggiBadan
        holder.tvPetugas?.text = balita.petugas
        holder.tvPosisiMenimbang?.text = balita.posisiMenimbang
        holder.tvUmurBalita?.text = balita.umurBalita

        holder.detailLayout.visibility = if (balita.expanded) View.VISIBLE else View.GONE

        holder.headerLayout.setOnClickListener {
            balita.expanded = !balita.expanded
            notifyItemChanged(position)
        }

        holder.btnEdit?.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Edit ${balita.nama}", Toast.LENGTH_SHORT).show()
        }

        holder.btnGrafik?.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Grafik ${balita.nama}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = list.size
}
