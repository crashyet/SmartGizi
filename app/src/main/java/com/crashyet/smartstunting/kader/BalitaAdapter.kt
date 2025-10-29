package com.crashyet.smartstunting.kader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.crashyet.smartstunting.R

class BalitaAdapter(
    private val list: MutableList<Balita>
) : RecyclerView.Adapter<BalitaAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headerLayout: LinearLayout = view.findViewById(R.id.headerLayout)
        val detailLayout: LinearLayout = view.findViewById(R.id.detailLayout)
        val tvNama: TextView = view.findViewById(R.id.tvNama)
        val tvTanggal: TextView = view.findViewById(R.id.tvTanggal)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)

        // Jika kamu menambahkan elemen lain di item_balita.xml, uncomment / tambahkan di sini:
        val tvBB: TextView? = view.findViewById(R.id.tvBB)         // optional
        val tvTB: TextView? = view.findViewById(R.id.tvTB)         // optional
        val tvPetugas: TextView? = view.findViewById(R.id.tvPetugas) // optional
        val btnEdit: Button? = view.findViewById(R.id.btnEdit)     // optional
        val btnGrafik: Button? = view.findViewById(R.id.btnGrafik) // optional
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

        // contoh isi detail jika kamu punya tvBB/tvTB di XML
        holder.tvBB?.text = "BB: ${balita.beratBadan}"
        holder.tvTB?.text = "TB: ${balita.tinggiBadan}"
        holder.tvPetugas?.text = "Petugas: ${balita.petugas}"

        holder.detailLayout.visibility = if (balita.expanded) View.VISIBLE else View.GONE

        holder.headerLayout.setOnClickListener {
            // toggle expand / collapse
            balita.expanded = !balita.expanded
            notifyItemChanged(position)
        }

        holder.btnEdit?.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Edit ${balita.nama}", Toast.LENGTH_SHORT).show()
            // TODO: navigasi ke screen edit
        }

        holder.btnGrafik?.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Grafik ${balita.nama}", Toast.LENGTH_SHORT).show()
            // TODO: buka fragment/activity grafik
        }
    }

    override fun getItemCount(): Int = list.size
}
