def agendar_citas_consola(hospital):
    # Buscar paciente
    paciente_seleccionado = None

    while paciente_seleccionado is None:
        cedula = input("Ingrese la cedula del paciente: ")
        paciente_seleccionado =hospital.buscar_paciente(int(cedula))

        if paciente_seleccionado is None:
            respuesta = input("No existe un paciente registrado con esta cedula. ¿Desea intentar de nuevo? (S/N): ")
            if respuesta.lower() == "n":
                return

    doctores_disponibles = []

    while len(doctores_disponibles) == 0:
        print("\nSeleccione el tipo de cita que requiere")
        print("1. General")
        print("2. Odontologia")
        print("3. Oftalmologia")

        tipoCita = int(input("Ingrese una opcion: "))

        if tipoCita == 1:
            doctores_disponibles = paciente_seleccionado.buscar_doctor_por_eps("General", hospital)

            if len(doctores_disponibles)>0:
                print(f"\nLista de doctores Generales para el tipo de eps {paciente_seleccionado.tipo_eps}:")
                for i in range(len(doctores_disponibles)):
                    print(f"{i+1}. {doctores_disponibles[i].nombre}")
            else:
                print(f"\nNo hay doctores que atiendan al tipo de eps {paciente_seleccionado.tipo_eps} para esta categoria, por favor seleccione otra")

        if tipoCita == 2:
            doctores_disponibles = paciente_seleccionado.buscar_doctor_por_eps("Odontologia", hospital)

            if len(doctores_disponibles) > 0:
                print(f"\nLista de Odontologos para el tipo de eps {paciente_seleccionado.tipo_eps}:")
                for i in range(len(doctores_disponibles)):
                    print(f"{i + 1}. {doctores_disponibles[i].nombre}")
            else:
                print(
                    f"\nNo hay doctores que atiendan al tipo de eps {paciente_seleccionado.tipo_eps} para esta categoria, por favor seleccione otra")

        if tipoCita == 3:
            doctores_disponibles = paciente_seleccionado.buscar_doctor_por_eps("Oftalmologia", hospital)

            if len(doctores_disponibles) > 0:
                print(f"\nLista de Oftalmologos para el tipo de eps {paciente_seleccionado.tipo_eps}:")
                for i in range(len(doctores_disponibles)):
                    print(f"{i + 1}. {doctores_disponibles[i].nombre}")
            else:
                print(
                    f"\nNo hay doctores que atiendan al tipo de eps {paciente_seleccionado.tipo_eps} para esta categoria, por favor seleccione otra")

    agenda_disponible = []

    while len(agenda_disponible)==0:
        numero_doctor = int(input("Seleccione el doctor con el que quiere la cita: "))
        doctor_seleccionado = doctores_disponibles[numero_doctor-1]
        agenda_disponible = doctor_seleccionado.mostrar_agenda_disponible()

        if len(agenda_disponible)>0:
            print("\nCitas disponibles: ")
            for i in range(len(agenda_disponible)):
                print(f"{i+1}. {agenda_disponible[i].fecha}")

            numero_cita = int(input("\nSeleccione la cita de su preferencia: "))
            cita_seleccionada = doctor_seleccionado.actualizar_agenda(paciente_seleccionado, numero_cita, agenda_disponible)

            print("\nSu cita ha sido asignada con exito")

            paciente_seleccionado.actualizar_historial_citas(cita_seleccionada)

            print("\nResumen de su cita: ")
            print(f"Fecha: {cita_seleccionada.fecha}")
            print(f"Paciente: {cita_seleccionada.paciente.nombre}")
            print(f"Doctor: {cita_seleccionada.doctor.nombre}")

            print("\nhistorial citas de historia clinica del paciente: ")
            for cita in paciente_seleccionado.historia_clinica.historial_citas:
                print(f"\nFecha: {cita.fecha}")
                print(f"Paciente: {cita.paciente.nombre}")
                print(f"Doctor: {cita.doctor.nombre}")

        else:
            print("\nEste doctor no tiene citas disponibles, por favor seleccione otro")


