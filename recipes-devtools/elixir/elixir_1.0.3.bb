HOMEPAGE = "http://elixir-lang.org/"
SUMMARY  = "Elixir is a dynamic, functional language designed for building scalable and maintainable applications"
DESCRIPTION = "Elixir leverages the Erlang VM, known for running low-latency, distributed and fault-tolerant systems, while also being successfully used in web development and the embedded software domain."
LICENSE  = "GPLv2"

LIC_FILES_CHKSUM = "file://LICENSE;md5=0c48e31d655fb0e9b1f60b931e652f47"

SRC_URI = "https://github.com/elixir-lang/elixir/archive/v1.0.3.tar.gz"

SRC_URI[md5sum] = "a7f44d73f5325fc8600044552b5abeae"
SRC_URI[sha256sum] = "ec6c4eaffcb771d91bdea9fb274600c624fd16b1891d194b3fcb2b521f52cc75"

DEPENDS = "erlang"
RDEPENDS_${PN} += "erlang erlang-ssl erlang-asn1 erlang-compiler erlang-crypto erlang-public-key erlang-syntax-tools erlang-mnesia"

S = "${WORKDIR}/${PN}-${PV}"

do_install() {
   install -d ${D}${bindir}
   install -d ${D}${libdir}
   
   install -m 755 ${S}/bin/elixir ${D}${bindir}
   install -m 755 ${S}/bin/elixirc ${D}${bindir}
   install -m 755 ${S}/bin/iex ${D}${bindir}
   install -m 755 ${S}/bin/mix ${D}${bindir}

   for dir in `ls ${S}/lib`; do
       install -d ${D}${libdir}/$dir
       cp -fr ${S}/lib/$dir/ebin ${D}${libdir}/$dir/
   done
}

FILES_${PN} = "${bindir} ${libdir}"
