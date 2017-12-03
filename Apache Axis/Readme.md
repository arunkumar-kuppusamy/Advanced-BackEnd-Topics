
	EXEMPLO DE USO UTILIZANDO WEBSERVICE
    // Use to get a proxy class for InicioWebServicePort
    private java.lang.String InicioWebServicePort_address = "http://wsphttp.dsv..........com.br/APLICATIVO/service/InicioWebService";
	
	
	ABRE UM RMI DE CONEXAO REMOTA
	    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (br.com.bradseg.vida.consultaapolicesws.funcao.webservice.InicioWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                br.com.bradseg.vida.consultaapolicesws.funcao.webservice.InicioWebServiceServiceSoapBindingStub _stub = new br.com.bradseg.vida.consultaapolicesws.funcao.webservice.InicioWebServiceServiceSoapBindingStub(new java.net.URL(InicioWebServicePort_address), this);
                _stub.setPortName(getInicioWebServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

	SETA O OBJETO DE ENTRADA QUE SERÁ SERIALIZADO
		SaldoPpraConsultaIn saldoConsultaPpraIn = new SaldoPpraConsultaIn();
		saldoConsultaPpraIn.setINPAGENCIA(planoSaldoPpraDTO.getAgencia());
		saldoConsultaPpraIn.setINPCONTA(planoSaldoPpraDTO.getConta());
		long cpf = Long.valueOf(String.valueOf(planoSaldoPpraDTO.getCpf()) + String.valueOf(FormatadorCodigo.preencheNumeroZeroEsquerda(planoSaldoPpraDTO.getCpfControle(), 2)));
		saldoConsultaPpraIn.setINPNUMEROCPF(cpf);
		saldoConsultaPpraIn.setINPRESTART(0);
		
		SaldoPpraConsultaOut saldoPpraConsultaOut = saldoPpraConsultaPDCAdapter.invoke(saldoConsultaPpraIn);//com apache axis ele faz o marshalling de objeto para xml, internamente.
		SaldoPpraConsultaOcorrenciaOut[] ocorrenciasPpraOut = saldoPpraConsultaOut.getSaldoPpraConsultaOcorrenciaOut();//CHAMA O WEBSERVICE
	
	CONEXAO COM O WEBSERVICE É FEITA VIA APACHE AXIS
		String endpoint = "http://ws.apache.org:5049/axis/services/echo";
		Service  service = new Service();
		Call     call    = (Call) service.createCall();
		call.setTargetEndpointAddress( new java.net.URL(endpoint) );
		call.setOperationName(new QName("http://soapinterop.org/", "echoString"));
		String ret = (String) call.invoke( new Object[] { "Hello!" } );
	
