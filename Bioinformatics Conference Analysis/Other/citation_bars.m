% creates a grouped bar graph of citation data
function h = citation_bars(data,std,ylimit,xtick,xlabels)  

    ax = axes;
    h = bar(data,'BarWidth',1);

    ax.YGrid = 'on';
    ax.GridLineStyle = '-';
    xticks(ax,xtick);

    xticklabels(ax,xlabels);
    xlabel ('Year');
    ylabel ('Citations');
    
    lg = legend('ISMB','PSB','RECOMB','ECCB','BCB','AutoUpdate','off','Location','northwest');
    hold on;

    ngroups = size(data, 1);
    nbars = size(data, 2);

    groupwidth = min(0.8, nbars/(nbars + 1.5));
    
    for i = 1:nbars
        x = (1:ngroups) - groupwidth/2 + (2*i-1) * groupwidth / (2*nbars);
        errorbar(x, data(:,i), std(:,i), 'k', 'linestyle', 'none');
    end

    ylim([0 ylimit]);
    xlim([0-0.25 24.25]);
    %xlim([0-0.25 25.25]); for graphing when treating ISMB-ECCB as a
    %separate conference
end