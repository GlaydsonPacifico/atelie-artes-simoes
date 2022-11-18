package br.atelieartessimoes.dtos;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ProductDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID id;

  private String title;

  private String description;

  private String image;

  private int price;

}
