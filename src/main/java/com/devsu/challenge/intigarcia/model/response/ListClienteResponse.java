package com.devsu.challenge.intigarcia.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListClienteResponse {

  private List<ClienteResponse> clienteResponses;
}
