def vacunacion_consola(hospital):
    # Buscar paciente
    paciente_seleccionado = None

    while paciente_seleccionado is None:
        cedula = input("Ingrese la cedula del paciente: ")
        paciente_seleccionado =hospital.buscar_paciente(int(cedula))

        if paciente_seleccionado is None:
            respuesta = input("No existe un paciente registrado con esta cedula. ¿Desea intentar de nuevo? (S/N): ")
            if respuesta.lower() == "n":
                return
    #Continuara... interacciones