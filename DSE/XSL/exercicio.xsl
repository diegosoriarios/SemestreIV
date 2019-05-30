<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <h2 style="background-color:blue; text-align:center">Lista de Preço</h2>
  <xsl:for-each select="cadastros/produtos/produto">
    <span style="font-weight:bold">Codigo:</span><span> <xsl:value-of select="codigo"/></span><br />
    <span style="font-weight:bold">Descrição:</span><span> <xsl:value-of select="nome"/></span><br />
    <span style="font-weight:bold">Unidade:</span><span> <xsl:value-of select="unidade"/></span><br />
    <span style="font-weight:bold">Preço:</span><span> <xsl:value-of select="preco"/></span><br />
    <xsl:if test="promocao = 'S'">
      <span style="background-color: yellow">Produto em promoção</span><br />
    </xsl:if>
    <br />
  </xsl:for-each>
</body>
</html>
</xsl:template>
</xsl:stylesheet>

