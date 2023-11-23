package com.billy.operations.api.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceTemplate {
    private String Version;
    private String Serie;
    private String Folio;
    private String Fecha;
    private int FormaPago;
    private String CondicionesDePago;
    private double SubTotal;
    private double Descuento;
    private String Moneda;
    private double TipoCambio;
    private double Total;
    private String TipoDeComprobante;
    private String Exportacion;
    private String MetodoPago;
    private String LugarExpedicion;
    private Emisor Emisor;
    private Receptor Receptor;
    private List<Concepto> Conceptos;
    private Impuestos Impuestos;

    @Data
    public static class Emisor {
        private String Rfc;
        private String Nombre;
        private int RegimenFiscal;
    }

    @Data
    public static class Receptor {
        private String Rfc;
        private String Nombre;
        private String DomicilioFiscalReceptor;
        private int RegimenFiscalReceptor;
        private String UsoCFDI;
    }

    @Data
    public static class Concepto {
        private Impuestos Impuestos;
        private String ClaveProdServ;
        private double Cantidad;
        private String ClaveUnidad;
        private String Unidad;
        private String Descripcion;
        private double ValorUnitario;
        private double Importe;
        private double Descuento;
        private String ObjetoImp;
        private ComplementoConcepto ComplementoConcepto;

        @Data
        public static class ComplementoConcepto {
            private List<Any> Any;

            @Data
            public static class Any {
                private VentaVehiculos VentaVehiculos;

                @Data
                public static class VentaVehiculos {
                    private String xmlnsVentavehiculos;
                    private String version;
                    private String ClaveVehicular;
                    private String Niv;
                }
            }
        }
    }

    @Data
    public static class Impuestos {
        private List<Traslado> Traslados;
        private double TotalImpuestosRetenidos;
        private double TotalImpuestosTrasladados;

        @Data
        public static class Traslado {
            private double Base;
            private String Impuesto;
            private String TipoFactor;
            private double TasaOCuota;
            private double Importe;
        }
    }
}
