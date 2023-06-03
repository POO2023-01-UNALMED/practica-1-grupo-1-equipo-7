from Python.src.gestor_aplicacion.servicios.servicio import Servicio


def facturacion_consola(hospital):
    # Buscar paciente
    paciente_seleccionado = None

    while paciente_seleccionado is None:
        cedula = input("Ingrese la cedula del paciente: ")
        paciente_seleccionado = hospital.buscar_paciente(int(cedula))

        if paciente_seleccionado is None:
            respuesta = input("No existe un paciente registrado con esta cedula. ¿Desea intentar de nuevo? (S/N): ")
            if respuesta.lower() == "n":
                return

    # Buscar servicios pendientes de pago
    servicios_sin_pagar = Servicio.obtener_servicios_sin_pagar(paciente_seleccionado)

    if len(servicios_sin_pagar) == 0:
        print("Usted no tiene ningún servicio pendiente de pago")
        return

    print("Servicios pendientes de pago:")
    for servicio in servicios_sin_pagar:
        print(servicio.descripcion_servicio())

    # Seleccionar servicio a pagar
    servicio_seleccionado = None

    print("Ingrese la ID del servicio que va a pagar:")
    while servicio_seleccionado is None:
        id_seleccionada = int(input())
        for servicio in servicios_sin_pagar:
            if servicio.id_servicio == id_seleccionada:
                servicio_seleccionado = servicio
                break
        if servicio_seleccionado is None:
            print("No existe el servicio seleccionado. Intente de nuevo.")

    # Calcular precio
    precio_servicio_seleccionado = paciente_seleccionado.calcular_precio(servicio_seleccionado)

    # Realizar pago
    print(f"Total a pagar: ${precio_servicio_seleccionado}")
    print("Realizar pago? (S/N)")

    if input().lower() == "s":
        servicio_seleccionado.validar_pago(paciente_seleccionado, servicio_seleccionado.id_servicio)
        print("Pago realizado")
    else:
        print("Pago cancelado")
