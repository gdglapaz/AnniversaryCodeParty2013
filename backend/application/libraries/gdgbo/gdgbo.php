<?php 

class GDGBo {

	protected $ci;
	private $version;

	public function __construct()
    {
        $this->version = '0.1';
        $this->ci =& get_instance();
        $this->ci->load->model('user_model');
	}

	public function getVersion(){
        return $this->version;
    }

    private function publish_json_response($res){
        header("Content-Type: application/json");
        header("Cache-Control: no-store");
        header("RESTful Server: GDGBo");
        header("RESTful Version: ".$this->getVersion());
        echo json_encode($res);
    }

    public function listar_usuario ($id) {
        $response = $this->ci->user_model->listarUsuario($id);
       	$this->publish_json_response($response);
    }

    public function salvar_usuario($params) {
    	$response = $this->ci->user_model->salvarUsuario($params);
    	$this->publish_json_response($response);
    }
}