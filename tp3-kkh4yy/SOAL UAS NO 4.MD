Lanjutkan dari berkas TP3 anda. Wajib menggunakan berkas TP3 bukan TP yang lain. 

Pada TP3 anda diminta membuat username password dan follower-nya. 

Kali ini anda diminta membuat prosesnya dilakukan dari membaca berkas.

Contoh input berkas:

inputData.txt (tidak termasuk garis)
--------------------
4
alif draelfan
zaidan merahbiru
grady Apollo2022
theosam adeecha
0 1 1 0
1 0 0 0
0 0 0 0
0 0 0 0
--------------------
Berkas diawali dengan sebuah bilangan n yang menyatakan jumlah user.
Kemudian dilanjutkan dengan n baris berisi dua kata, kata pertama menyatakan username kata kedua menyatakan password. (Password dan username tidak boleh berisi spasi).
Kemudian dilanjutkan barisan berupa bilangan bulat positif (0 dan 1) dalam bentuk matriks ukuran n x n.

Matriks tersebut akan dibaca dan disimpan dalam matriks hubungan follow sebagaimana dipaparkan pada deskripsi TP3.
Ubah simulasi program nya untuk mempermudah melihat efek dari load berkas tersebut. Sehingga program akan menampilkan seperti contoh pada deskripsi TP3 tanpa harus mengetik cukup membaca berkas saja. 

Perhatikan juga isu-isu exception yang perlu diantisipasi saat membaca berkas,
misalnya bagaimana bila berkasnya error. Contoh handling exception seperti yang dilakukan pada contoh program yang sudah dipublish di scele terkait tema IO dan Exception Handling.

Kriteria evaluasi

Nilai 4: Program sesuai harapan, bisa berlaku untuk beberapa jumlah user, exception terjadi baik, dan disertai unit test terhadap method yang dibuat (minimal unit test untuk membaca berkas)
Nilai 3: Program berjalan baik, namun tidak bisa handle kasus2 khusus exception atau unit testnya tdak ada. 
Nilai 2: Program bisa membaca berkas untuk dapat usernama password tapi tidak bisa baca matriks following atau sebaliknya. 
Nilai 1: Ada tambahan yang significant terkait soal tapi program tidak bisa dicompile.
Nilai 0: Ada tambahan tapi tidak signifincant terkait soal. 