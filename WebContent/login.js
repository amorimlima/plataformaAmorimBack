$(document).ready(function () {
        $("#btnSubmit").click(function () {
            logar();
        });
    });

function logar() {
	

        $("#frm").on("submit", function(event) {
            event.preventDefault();

            $.ajax({
                url: "http://localhost:8082/plataformaAmorim/Logar",
                type: "post",
                crossDomain: true,
                data: $(this).serialize(),
                success: function(d) {
                    alert(d);
    
                }
            });
        });

	
}
