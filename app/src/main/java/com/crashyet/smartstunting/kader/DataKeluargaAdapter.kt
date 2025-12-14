package com.crashyet.smartstunting.kader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.crashyet.smartstunting.R

class DataKeluargaAdapter(
    private val list: ArrayList<DataKeluargaModel>,
    private val onLihat: (DataKeluargaModel) -> Unit,
    private val onUbah: (DataKeluargaModel) -> Unit,
    private val onHapus: (DataKeluargaModel) -> Unit
) : RecyclerView.Adapter<DataKeluargaAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val headerLayout: LinearLayout = v.findViewById(R.id.headerLayoutDataKeluarga)

        // Header
        val tvNamaBalita: TextView = v.findViewById(R.id.tvNamaBalita)
        val tvNamaIbu: TextView = v.findViewById(R.id.tvNamaIbu)
        val tvNamaKecamatan: TextView = v.findViewById(R.id.tvNamaKecamatan)
        val tvNamaPosyandu: TextView = v.findViewById(R.id.tvNamaPosyandu)

        // Detail
        val detailLayout: ConstraintLayout = v.findViewById(R.id.detailLayoutKeluarga)
        val tvNoKK: TextView = v.findViewById(R.id.tvNoKK)
        val tvStatusEkonomi: TextView = v.findViewById(R.id.tvStatusEkonomi)
        val tvAlamat: TextView = v.findViewById(R.id.tvAlamat)
        val tvRTRW: TextView = v.findViewById(R.id.tvRTRW)
        val tvJumlahAnak: TextView = v.findViewById(R.id.tvJumlahAnak)
        val tvTahapanKS: TextView = v.findViewById(R.id.tvTahapanKS)
        val tvStatusKEKIbu: TextView = v.findViewById(R.id.tvStatusKEKIbu)


        // Buttons
        val btnLihat: Button = v.findViewById(R.id.btnLihat)
        val btnUbah: Button = v.findViewById(R.id.btnUbah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_data_keluarga, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        // Bind header
        holder.tvNamaBalita.text = item.namaBalita
        holder.tvNamaIbu.text = item.namaIbu
        holder.tvNamaKecamatan.text = item.kecamatan
        holder.tvNamaPosyandu.text = item.posyandu

        // Bind detail
        holder.tvNoKK.text = item.noKK
        holder.tvStatusEkonomi.text = item.statusEkonomi
        holder.tvAlamat.text = item.alamat
        holder.tvRTRW.text = item.rtRw
        holder.tvJumlahAnak.text = item.jumlahAnak
        holder.tvTahapanKS.text = item.tahapanKS
        holder.tvStatusKEKIbu.text = item.statusKEKIbu

        // Expand/Collapse
        holder.detailLayout.visibility = if (item.expanded) View.VISIBLE else View.GONE

        holder.headerLayout.setOnClickListener {
            item.expanded = !item.expanded
            notifyItemChanged(position)
        }

        // Actions
        holder.btnLihat.setOnClickListener { onLihat(item) }
        holder.btnUbah.setOnClickListener { onUbah(item) }
    }
}
