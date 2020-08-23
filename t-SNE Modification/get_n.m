function [eta_new,dbt_new] = get_n(gradient,eta,theta,phi,kappa,dbt)
    dbt_new = zeros(size(gradient,1),size(gradient,2));
    eta_new = zeros(size(gradient,1),size(gradient,2));
    
    for i = 1:size(gradient,1)
        for j = 1:size(gradient,2)
            
            dbt_new(i,j) = (1 - theta) * gradient(i,j) + theta * dbt(i,j);
            
            if (dbt(i,j) * gradient(i,j)) > 0
                eta_new(i,j) = eta(i,j) + kappa; 
            elseif (dbt(i,j) * gradient(i,j)) < 0
                eta_new(i,j) = eta(i,j) - phi * eta(i,j);
            else
                eta_new(i,j) = eta(i,j);
            end
        end
    end
end