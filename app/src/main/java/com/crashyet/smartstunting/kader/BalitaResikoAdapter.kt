package com.crashyet.smartstunting.kader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crashyet.smartstunting.R

class BalitaResikoAdapter(
    private val list: MutableList<BalitaResiko>
) : RecyclerView.Adapter<BalitaResikoAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headerLayout: View = view.findViewById(R.id.headerLayoutResiko)
        val detailLayout: View = view.findViewById(R.id.detailLayoutResiko)
        val tvNama: TextView = view.findViewById(R.id.tvNamaResiko)
        val tvTanggal: TextView = view.findViewById(R.id.tvTanggalResiko)
        val tvJenisKelamin: TextView = view.findViewById(R.id.tvJenisKelaminResiko)
        val tvBb: TextView = view.findViewById(R.id.tvBbResiko)
        val tvTb: TextView = view.findViewById(R.id.tvTbResiko)
        val tvLevelRisiko: TextView = view.findViewById(R.id.tvLvlRisiko)
        val tvStatusGizi: TextView = view.findViewById(R.id.tvStatusGiziResiko)
        val tvZBbU: TextView = view.findViewById(R.id.tvZBbU)
        val tvZTbU: TextView = view.findViewById(R.id.tvZTbU)
        val tvZBbTb: TextView = view.findViewById(R.id.tvZBbTb)
        val tvStatusStunting: TextView = view.findViewById(R.id.tvStatusStunting)
        val btnLihat: Button = view.findViewById(R.id.btnLihat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_balita_beresiko, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val balita = list[position]

        // Header
        holder.tvNama.text = balita.nama
        holder.tvTanggal.text = balita.tanggal
        holder.tvJenisKelamin.text = balita.jenisKelamin

        // Detail
        holder.tvBb.text = "${balita.bb}"
        holder.tvTb.text = "${balita.tb}"
        holder.tvLevelRisiko.text = "${balita.levelRisiko}"
        holder.tvStatusGizi.text = "${balita.statusGizi}"
        holder.tvZBbU.text = "${balita.zBbU}"
        holder.tvZTbU.text = "${balita.zTbU}"
        holder.tvZBbTb.text = "${balita.zBbTb}"
        holder.tvStatusStunting.text = "${balita.statusStunting}"

        // Expand / Collapse logic
        holder.detailLayout.visibility = if (balita.expanded) View.VISIBLE else View.GONE

        holder.headerLayout.setOnClickListener {
            balita.expanded = !balita.expanded
            notifyItemChanged(position)
        }

        holder.btnLihat.setOnClickListener {
            // nanti bisa buat navigasi ke detail / grafik
        }
    }

    override fun getItemCount(): Int = list.size
}
