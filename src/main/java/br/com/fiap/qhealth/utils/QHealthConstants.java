package br.com.fiap.qhealth.utils;

public final class QHealthConstants {

    private QHealthConstants(){}

    public static final String OK = "OK";
    public static final String NO_CONTENT = "No Content";
    public static final String NOT_FOUND = "Not Found";
    public static final String BAD_REQUEST = "Bad Request";
    public static final String UNPROCESSABLE_ENTITY = "Unprocessable Entity";

    public static final String HTTP_STATUS_CODE_200 = "200";
    public static final String HTTP_STATUS_CODE_201 = "201";
    public static final String HTTP_STATUS_CODE_204 = "204";
    public static final String HTTP_STATUS_CODE_400 = "400";
    public static final String HTTP_STATUS_CODE_404 = "404";
    public static final String HTTP_STATUS_CODE_422 = "422";

    public static final String V1_PACIENTE = "/api/v1/pacientes";

    public static final String ID_INVALIDO = "ID inválido.";
    public static final String ID_NAO_ENCONTRADO = "ID não encontrado.";

    public static final String PACIENTE_CRIADO_COM_SUCESSO = "Paciente criado com sucesso.";
    public static final String ERRO_AO_CRIAR_PACIENTE = "Erro ao criar paciente.";
    public static final String ERRO_AO_ALTERAR_PACIENTE = "Erro ao alterar paciente.";
    public static final String PACIENTE_NAO_ENCONTRADO = "Paciente não encontrado.";
    public static final String ERRO_AO_DELETAR_PACIENTE = "Erro ao deletar paciente.";

    public static final String ERRO_AO_CRIAR_ANAMNESE = "Erro ao criar ANAMNESE.";
    public static final String ERRO_AO_ALTERAR_ANAMNESE = "Erro ao alterar ANAMNESE.";
    public static final String ANAMNESE_NAO_ENCONTRADO = "ANAMNESE não encontrado.";
    public static final String ERRO_AO_DELETAR_ANAMNESE = "Erro ao deletar ANAMNESE.";
}
