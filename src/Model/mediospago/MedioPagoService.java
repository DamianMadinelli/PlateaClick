package Model.mediospago;

import java.util.HashMap;
import java.util.Map;

import api.IMedioPago;
import exceptions.MetodoDePagoNoEncontradoException;

public class MedioPagoService {
    private final Map<String, IMedioPago> mediosPago = new HashMap<>();

    public MedioPagoService() {
        mediosPago.put("efectivo", new Efectivo());
        mediosPago.put("tarjeta de credito - 2 cuotas", new TarjetaCredito2Cuotas());
        mediosPago.put("tarjeta de credito - 3 cuotas", new TarjetaCredito3Cuotas());
        mediosPago.put("tarjeta de credito - 6 cuotas", new TarjetaCredito6Cuotas());
        mediosPago.put("tarjeta de debito", new TarjetaDebito());
    }

    public IMedioPago obtenerMedioPago(String tipoMedioPago) throws MetodoDePagoNoEncontradoException {
        IMedioPago medio = mediosPago.get(tipoMedioPago.toLowerCase());
        if (medio == null) {
            throw new MetodoDePagoNoEncontradoException();
        }
        return medio;
    }
}
