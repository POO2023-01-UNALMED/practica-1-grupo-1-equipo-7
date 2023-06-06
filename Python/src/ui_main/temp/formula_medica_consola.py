from src.gestor_aplicacion.servicios.formula import Formula


def facturacion_consola(hospital):
    paciente_seleccionado = None

    while paciente_seleccionado is None:
        cedula = input("Ingrese la cedula del paciente: ")
        paciente_seleccionado = hospital.buscar_paciente(int(cedula))

        if paciente_seleccionado is None:
            respuesta = input("No existe un paciente registrado con esta cedula. ¿Desea intentar de nuevo? (S/N): ")
            if respuesta.lower() == "n":
                return
    formula_paciente = Formula(paciente_seleccionado)
    lista_medicamentos = []
    print("¿Que enfermedad deseas tratar?")
    enfermedad_tratar = None
    if len(paciente_seleccionado.historia_clinica().enfermedades()) < 0:
        print("Sin enfermedades")
    while True:
        for i in range(len(paciente_seleccionado.historia_clinica().enfermedades())):
            print(f"{i + 1}.{paciente_seleccionado.historia_clinica().enfermedades()[i]}")
        opc_enf = int(input("Seleccione el indice de la enfermedad a tratar: "))

        if opc_enf <= len(paciente_seleccionado.historia_clinica().enfermedades()) and opc_enf > 0:
            enfermedad_tratar = paciente_seleccionado.historia_clinica().enfermedades()[opc_enf - 1]
            break
        else:
            print("Opcion invalida")
    doctores_cita = paciente_seleccionado.historia_clinica().buscar_cita(enfermedad_tratar.nombre(), hospital)
    if len(doctores_cita == 0):
        print("No hay doctores para tratar esa enfermedad")
    doctor_escogido = None
    agregar_otro = True
    while agregar_otro:
        print("Doctores disponibles: ")
        for i in range(len(doctores_cita)):
            print(f"{i + 1}.{doctores_cita[i].nombre()}")
        opc_doc = int(input("Seleccione el indice del doctor: "))

        if opc_doc > 0 and opc_doc <= len(doctores_cita):
            doctor_escogido = doctores_cita[opc_doc - 1]
            break
        else:
            print("Opcion invalida")

        while True:
            print(f"Hola {doctor_escogido.nombre()}")
            medicamentos_enf = paciente_seleccionado.med_enfermedad(enfermedad_tratar, hospital)
            if len(medicamentos_enf) == 0:
                print("No hay medicamentos disponibles")
                break

            while True:
                for i in range(len(medicamentos_enf)):
                    print(f"{i + 1}.{medicamentos_enf[i]}, Cantidad: {medicamentos_enf[i].cantidad()}")

                opc_med = int(input("Seleccione que medicamento agregar"))
                if opc_med <= 0 or opc_med > len(medicamentos_enf):
                    print("Opcion invalida")
                else:
                    medicamento_escogido = medicamentos_enf[opc_med - 1]
                    medicamento_escogido.eliminar_cantidad()
                    lista_medicamentos.append(medicamento_escogido)
                    break
            formula_paciente.lista_meds(lista_medicamentos)
            print(f"Esta es tu lista actual de medicamentos:")
            for med in lista_medicamentos:
                print(med)
            while True:
                opc_bucle = input("Desea agregar otro medicamento? (s/n)")
                if (opc_bucle == "s" or opc_bucle == "n"):
                    agregar_otro = False
                    break
                else:
                    print("Opcion Invalida")
    paciente_seleccionado.historia_clinica().agregar_formula(formula_paciente)
    print(formula_paciente)
    lista_medicamentos = []