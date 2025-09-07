package br.com.fiap.qhealth.utils;

import br.com.fiap.qhealth.dto.request.PacienteBodyRequest;
import br.com.fiap.qhealth.dto.response.PacienteBodyResponse;
import br.com.fiap.qhealth.exception.ResourceNotFoundException;
import br.com.fiap.qhealth.model.Paciente;
import org.modelmapper.ModelMapper;

import java.util.UUID;

import static br.com.fiap.qhealth.utils.QHealthConstants.ID_INVALIDO;
import static java.util.regex.Pattern.matches;

public class QHealthUtils {

    private static final String REGEX_UUID = "^[0-9a-f]{8}-[0-9a-f]{4}-[4][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$";

    public static void uuidValidator(UUID id) {
        if (!matches(REGEX_UUID, id.toString())) {
            throw new ResourceNotFoundException(ID_INVALIDO);
        }
    }

    public static Paciente convertToPaciente(PacienteBodyRequest pacienteRequest) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pacienteRequest, Paciente.class);
    }

    public static PacienteBodyResponse convertToPaciente(Paciente pacienteEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pacienteEntity, PacienteBodyResponse.class);
    }
}
