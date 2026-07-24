# 🎭 PlateaClick

Sistema de venta de entradas para teatro desarrollado en Java. Permite gestionar funciones, actores, disponibilidad de asientos y el flujo completo de compra de entradas con distintos medios de pago.

---

## Características principales

- Gestión de funciones (nombre, fecha, horario, duración, actores)
- Administración de la sala con seis secciones de asientos independientes
- Selección de ubicaciones mediante el patrón **Decorator**
- Flujo de compra completo: agregar entradas, elegir medio de pago y finalizar
- Eliminación de entradas antes de confirmar la compra
- Dos roles de usuario: **Administrador** y **Cliente**
- Menú interactivo por consola (demo en `demoTest/mainTest.java`)

---

## Requisitos

- Java 11 o superior (el proyecto usa `module-info.java`)
- Eclipse IDE (incluye `.classpath` y `.project`) o cualquier IDE compatible con proyectos Java modulares

---

## Estructura del proyecto

```
src/
├── api/                        # Interfaces públicas
│   ├── IEntrada.java
│   └── IMedioPago.java
├── Aplicacion/
│   └── ControllerTeatro.java   # Controlador principal (punto de entrada de la lógica)
├── Model/
│   ├── AdministradorAsientos.java   # Gestión de disponibilidad por sección
│   ├── Sala.java                    # Singleton: configuración de la sala
│   ├── compra/
│   │   └── Compra.java
│   ├── entrada/                     # Tipos de entrada (Decorator Pattern)
│   │   ├── EntradaBase.java
│   │   ├── EntradaDecorator.java
│   │   ├── EntradaService.java
│   │   ├── Platea.java
│   │   ├── PalcoAlto.java
│   │   ├── PalcoBajo.java
│   │   ├── Cazuela.java
│   │   ├── Tertulia.java
│   │   └── Paraiso.java
│   ├── mediospago/                  # Medios de pago (Strategy Pattern)
│   │   ├── Efectivo.java
│   │   ├── TarjetaDebito.java
│   │   ├── TarjetaCredito.java
│   │   ├── TarjetaCredito2Cuotas.java
│   │   ├── TarjetaCredito3Cuotas.java
│   │   ├── TarjetaCredito6Cuotas.java
│   │   └── MedioPagoService.java
│   └── teatro/
│       ├── Teatro.java              # Singleton: repositorio de funciones
│       ├── Funcion.java
│       ├── Administrador.java
│       ├── Cliente.java
│       └── actores/
│           ├── Actor.java
│           └── GrupoDeActores.java
├── exceptions/                      # Excepciones de dominio
├── demoTest/
│   ├── mainTest.java                # Demo interactivo completo
│   └── mainMiniDemos.java
└── module-info.java
```

---

## Patrones de diseño aplicados

**Decorator** — Las entradas se construyen apilando decoradores sobre `EntradaBase`. Cada sección agrega su costo al total y ocupa un asiento automáticamente:

```
EntradaBase  →  Platea  →  PalcoAlto   (una compra con dos ubicaciones para la misma función)
```

**Singleton** — `Teatro` y `Sala` tienen una única instancia compartida durante la ejecución, accesible vía `getInstance()`.

**Strategy** — Los medios de pago implementan `IMedioPago` con su propio cálculo del valor final.

**Service Layer** — `EntradaService` y `MedioPagoService` centralizan la lógica de selección y construcción, manteniendo al controlador delgado.

---

## Secciones y precios de entradas

| Sección     | Precio base |
|-------------|-------------|
| Paraíso     | $150        |
| Platea      | $120        |
| Palco Bajo  | $100        |
| Palco Alto  | $80         |
| Cazuela     | $60         |
| Tertulia    | $40         |

El precio final de cada entrada suma el costo de la ubicación más el costo de la función (`duración en minutos × costoPorMin`, por defecto `$1/min`).

---

## Medios de pago

| Medio de pago              | Ajuste sobre el total |
|----------------------------|-----------------------|
| Efectivo                   | −10 % (descuento)     |
| Tarjeta de débito          | Sin recargo           |
| Tarjeta de crédito 2 cuotas | +6 %                 |
| Tarjeta de crédito 3 cuotas | +12 %                |
| Tarjeta de crédito 6 cuotas | +20 %                |

---

## Cómo ejecutar el demo

1. Importar el proyecto en Eclipse (o compilar con `javac` respetando el `module-info.java`).
2. Ejecutar `demoTest.mainTest`.
3. Seguir el menú interactivo:

```
--- Menu Principal ---
0. Finalizar programa
1. Ingresar como Administrador
2. Ingresar como Usuario
```

El demo precarga una sala con 1000 asientos y dos funciones de ejemplo para poder probar el flujo completo sin configuración adicional.

---

## Flujo de compra (Cliente)

```
1. Seleccionar función (por ID)
2. Elegir ubicación disponible (platea, palco alto, palco bajo, cazuela, tertulia, paraíso)
3. Repetir para agregar más ubicaciones a la misma función o entradas a otras funciones
4. Seleccionar medio de pago
5. Confirmar y finalizar la compra → se imprime el ticket con el total
```

---

## Excepciones de dominio

| Excepción                         | Cuándo se lanza                                          |
|-----------------------------------|----------------------------------------------------------|
| `AsientoInexistenteException`     | Se intenta ocupar/liberar una sección que no existe      |
| `NoHayAsientosOcupadosException`  | Se intenta liberar un asiento que ya estaba libre        |
| `TipoEntradaInvalidaException`    | El tipo de ubicación ingresado no coincide con ninguna sección |
| `FuncionNoEncotradaException`     | No existe ninguna función con el ID solicitado           |
| `ActorNoEncontradoException`      | No existe ningún actor con el ID solicitado en la función |
| `MetodoDePagoNoEncontradoException` | El string ingresado no coincide con ningún medio de pago |

---

## Posibles mejoras

- Agregar persistencia (base de datos o archivo) para no perder el estado entre ejecuciones
- Implementar una interfaz gráfica o API REST sobre el `ControllerTeatro` existente
- Corregir el typo `FuncionNoEncotradaException` → `FuncionNoEncontradaException`
- Hacer configurable el `costoPorMin` al crear cada función desde el menú de administrador
- Agregar tests unitarios con JUnit para los servicios y el flujo de compra
