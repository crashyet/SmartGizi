package com.crashyet.smartstunting.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crashyet.smartstunting.data.model.DataKeluargaModel
import com.crashyet.smartstunting.R

class DataKeluargaAdapter(private val listKeluarga: List<DataKeluargaModel>) :
    RecyclerView.Adapter<DataKeluargaAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val namaAyah: TextView = view.findViewById(R.id.tvNamaAyah)
        val namaIbu: TextView = view.findViewById(R.id.tvNamaIbu)
        val desa: TextView = view.findViewById(R.id.tvDesa)
        val posyandu: TextView = view.findViewById(R.id.tvPosyandu)
        val noKK: TextView = view.findViewById(R.id.tvKK)
        val statusEkonomi: TextView = view.findViewById(R.id.tvStatusEkonomi)
        val alamat: TextView = view.findViewById(R.id.tvAlamat)
        val rtRw: TextView = view.findViewById(R.id.tvRtRw)
        val jumlahAnak: TextView = view.findViewById(R.id.tvJumlahAnak)
        val tahapanKs: TextView = view.findViewById(R.id.tvTahapanKS)
        val statusKekIbu: TextView = view.findViewById(R.id.tvStatusKEK)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_data_keluarga, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val keluarga = listKeluarga[position]

        holder.namaAyah.text = keluarga.nama_ayah
        holder.namaIbu.text = keluarga.nama_ibu
        holder.desa.text = keluarga.desa
        holder.posyandu.text = keluarga.posyandu
        holder.noKK.text = keluarga.no_kk
        holder.statusEkonomi.text = keluarga.status_ekonomi
        holder.alamat.text = keluarga.alamat
        holder.rtRw.text = keluarga.rt_rw
        holder.jumlahAnak.text = keluarga.jumlah_anak
        holder.tahapanKs.text = keluarga.tahapan_ks
        holder.statusKekIbu.text = keluarga.status_kek_ibu

        // Contoh logic warna (opsional)
//        if (keluarga.status_ekonomi.equals("Menengah ke bawah", ignoreCase = true)) {
//            holder.statusEkonomi.setTextColor(
//                holder.itemView.context.getColor(R.color.red)
//            )
//        } else {
//            holder.statusEkonomi.setTextColor(
//                holder.itemView.context.getColor(R.color.green)
//            )
//        }
//
//        if (keluarga.status_kek_ibu.equals("Kek", ignoreCase = true)) {
//            holder.statusKekIbu.setBackgroundResource(R.drawable.bg_status_nonaktif)
//            holder.statusKekIbu.setTextColor(holder.itemView.context.getColor(R.color.white))
//        } else {
//            holder.statusKekIbu.setBackgroundResource(R.drawable.bg_status_aktif)
//            holder.statusKekIbu.setTextColor(holder.itemView.context.getColor(R.color.white))
//        }
    }

    override fun getItemCount() = listKeluarga.size
}