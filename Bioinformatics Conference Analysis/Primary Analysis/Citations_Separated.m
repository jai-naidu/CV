%Read code from .json and add '0' as the number of citations for years
%where the given paper had no citations
data = jsondecode(fileread('Final_Citation_Data.json'));
% data = fill_dates(unfilled_data); % the provided data already meets the
%                                     above condition

%Creates a matrix of data for each year per conference and applies the
%specified filter to each matrix (average, median, median of top 10
%citations per paper) and determines the confidence intervals for each year
%for ISMB
for i = 1993 : 2016
    if i < 2004 || i == 2005 || i > 2005 && mod(i,2) == 0
        temp = tomatrix('ISMB', i, data);
        ISMB_av(i - 1992) = conference_average(temp);
        ISMB_med(i - 1992) = conference_median(temp);
        ISMB_top(i - 1992) = conference_top10(temp);
        ISMB_std_av(i - 1992) = get_std(temp,"av");
        ISMB_std_med(i - 1992) = get_std(temp,"med");
        ISMB_std_top(i - 1992) = get_std(temp,"top");
    else
        ISMB_av(i - 1992) = 0;
        ISMB_med(i - 1992) = 0;
        ISMB_top(i - 1992) = 0;
        ISMB_std_av(i - 1992) = 0;
        ISMB_std_med(i - 1992) = 0;
        ISMB_std_top(i - 1992) = 0;
    end
end

%Creates a matrix of data for each year per conference and applies the
%specified filter to each matrix (average, median, median of top 10
%citations per paper) and determines the confidence intervals for each year
%for ISMB-ECCB
for i = 1993 : 2016
    if i == 2004 || i > 2005 && mod(i,2) > 0
        temp = tomatrix('ISMB-ECCB', i, data);
        ISMBECCB_av(i - 1992) = conference_average(temp);
        ISMBECCB_med(i - 1992) = conference_median(temp);
        ISMBECCB_top(i - 1992) = conference_top10(temp);
        ISMBECCB_std_av(i - 1992) = get_std(temp,"av");
        ISMBECCB_std_med(i - 1992) = get_std(temp,"med");
        ISMBECCB_std_top(i - 1992) = get_std(temp,"top");
     else
        ISMBECCB_av(i - 1992) = 0;
        ISMBECCB_med(i - 1992) = 0;
        ISMBECCB_top(i - 1992) = 0;
        ISMBECCB_std_av(i - 1992) = 0;
        ISMBECCB_std_med(i - 1992) = 0;
        ISMBECCB_std_top(i - 1992) = 0;
    end
end
    
%Creates a matrix of data for each year per conference and applies the
%specified filter to each matrix (average, median, median of top 10
%citations per paper) and determines the confidence intervals for each year
%for ECCB
for i = 1993 : 2016
    if (i < 2004 && i >= 2002) || i == 2005 || i > 2005 && mod(i,2) == 0
        temp = tomatrix('ECCB', i, data);
        ECCB_av(i - 1992) = conference_average(temp);
        ECCB_med(i - 1992) = conference_median(temp);
        ECCB_top(i - 1992) = conference_top10(temp);
        ECCB_std_av(i - 1992) = get_std(temp,"av");
        ECCB_std_med(i - 1992) = get_std(temp,"med");
        ECCB_std_top(i - 1992) = get_std(temp,"top");
    else
        ECCB_av(i - 1992) = 0;
        ECCB_med(i - 1992) = 0;
        ECCB_top(i - 1992) = 0;
        ECCB_std_av(i - 1992) = 0;
        ECCB_std_med(i - 1992) = 0;
        ECCB_std_top(i - 1992) = 0;
    end
end

%Creates a matrix of data for each year per conference and applies the
%specified filter to each matrix (average, median, median of top 10
%citations per paper) and determines the confidence intervals for each year
%for PSB
for i = 1993 : 2016
    if i >= 1996
        temp = tomatrix('PSB', i, data);
        PSB_av(i - 1992) = conference_average(temp);
        PSB_med(i - 1992) = conference_median(temp);
        PSB_top(i - 1992) = conference_top10(temp);
        PSB_std_av(i - 1992) = get_std(temp,"av");
        PSB_std_med(i - 1992) = get_std(temp,"med");
        PSB_std_top(i - 1992) = get_std(temp,"top");
    else
        PSB_av(i - 1992) = 0;
        PSB_med(i - 1992) = 0;
        PSB_top(i - 1992) = 0;
        PSB_std_av(i - 1992) = 0;
        PSB_std_med(i - 1992) = 0;
        PSB_std_top(i - 1992) = 0;
    end
end

%Creates a matrix of data for each year per conference and applies the
%specified filter to each matrix (average, median, median of top 10
%citations per paper) and determines the confidence intervals for each year
%for BCB
for i = 1993 : 2016
    if i >= 2010 && i <= 2017
        temp = tomatrix('BCB', i, data);
        BCB_av(i - 1992) = conference_average(temp);
        BCB_med(i - 1992) = conference_median(temp);
        BCB_top(i - 1992) = conference_top10(temp);
        BCB_std_av(i - 1992) = get_std(temp,"av");
        BCB_std_med(i - 1992) = get_std(temp,"med");
        BCB_std_top(i - 1992) = get_std(temp,"top");
    else
        BCB_av(i - 1992) = 0;
        BCB_med(i - 1992) = 0;
        BCB_top(i - 1992) = 0;
        BCB_std(i - 1992) = 0;
    end
end

%Creates a matrix of data for each year per conference and applies the
%specified filter to each matrix (average, median, median of top 10
%citations per paper) and determines the confidence intervals for each year
%for RECOMB
for i = 1993 : 2016
    if i >= 1997 && i <= 2017
        temp = tomatrix('RECOMB', i, data);
        RECOMB_av(i - 1992) = conference_average(temp);
        RECOMB_med(i - 1992) = conference_median(temp);
        RECOMB_top(i - 1992) = conference_top10(temp);
        RECOMB_std_av(i - 1992) = get_std(temp,"av");
        RECOMB_std_med(i - 1992) = get_std(temp,"med");
        RECOMB_std_top(i - 1992) = get_std(temp,"top");
    else
        RECOMB_av(i - 1992) = 0;
        RECOMB_med(i - 1992) = 0;
        RECOMB_top(i - 1992) = 0;
        RECOMB_std_av(i - 1992) = 0;
        RECOMB_std_med(i - 1992) = 0;
        RECOMB_std_top(i - 1992) = 0;
    end
end

%----------------------------------------------------------

%creates a matrix for all of the data so it can be visualized in a grouped
%bar graph
icounter = 1;
iecounter = 1;
ecounter = 1;
pcounter = 1;
acounter = 1;
rcounter = 1;

for i = 1:24
    for j = 1:6
        if j == 1
            graph_av(i,j) = ISMB_av(icounter);
            graph_med(i,j) = ISMB_med(icounter);
            graph_top(i,j) = ISMB_top(icounter);
            std_av(i,j) = ISMB_std_av(icounter);
            std_med(i,j) = ISMB_std_med(icounter);
            std_top(i,j) = ISMB_std_top(icounter);
            icounter = icounter + 1;
        elseif j == 2
            graph_av(i,j) = ISMBECCB_av(iecounter);
            graph_med(i,j) = ISMBECCB_med(iecounter);
            graph_top(i,j) = ISMBECCB_top(iecounter);
            std_av(i,j) = ISMBECCB_std_av(iecounter);
            std_med(i,j) = ISMBECCB_std_med(iecounter);
            std_top(i,j) = ISMBECCB_std_top(iecounter);
            iecounter = iecounter + 1;
        elseif j == 3
            graph_av(i,j) = PSB_av(pcounter);
            graph_med(i,j) = PSB_med(pcounter);
            graph_top(i,j) = PSB_top(pcounter);
            std_av(i,j) = PSB_std_av(pcounter);
            std_med(i,j) = PSB_std_med(pcounter);
            std_top(i,j) = PSB_std_top(pcounter);
            pcounter = pcounter + 1;
        elseif j == 4
            graph_av(i,j) = RECOMB_av(rcounter);
            graph_med(i,j) = RECOMB_med(rcounter);
            graph_top(i,j) = RECOMB_top(rcounter);
            std_av(i,j) = RECOMB_std_av(rcounter);
            std_med(i,j) = RECOMB_std_med(rcounter);
            std_top(i,j) = RECOMB_std_top(rcounter);
            rcounter = rcounter + 1;
        elseif j == 5
            graph_av(i,j) = ECCB_av(ecounter);
            graph_med(i,j) = ECCB_med(ecounter);
            graph_top(i,j) = ECCB_top(ecounter);
            std_av(i,j) = ECCB_std_av(ecounter);
            std_med(i,j) = ECCB_std_med(ecounter);
            std_top(i,j) = ECCB_std_top(ecounter);
            ecounter = ecounter + 1;
        elseif j == 6
            graph_av(i,j) = BCB_av(acounter);
            graph_med(i,j) = BCB_med(acounter);
            graph_top(i,j) = BCB_top(acounter);
            std_av(i,j) = BCB_std_av(acounter);
            std_med(i,j) = BCB_std_med(acounter);
            std_top(i,j) = BCB_std_top(acounter);
            acounter = acounter + 1;
        end
    end
end

%-----------------------------GRAPHS-----------------------------


xlabels = {'1993','1994','1995','1996','1997','1998','1999', '2000',...
    '2001', '2002', '2003', '2004', '2005', '2006', '2007', '2008',...
    '2009', '2010', '2011', '2012', '2013', '2014', '2015', '2016'};

figure(1)
citation_bars(graph_av,std_av,19,(1:1:24),xlabels);
title('Citations per Paper per Year');

figure(2)
citation_bars(graph_med,std_med,10,(1:1:24),xlabels);
title('Citations of Median Paper per Year');

figure(3)
citation_bars(graph_top,std_top,43,(1:1:24),xlabels);
title('Citations of Median of Top 10 Papers per Year');

%----------------------------------

%calculates p-values
avmat = pvalmat(graph_av)
medmat = pvalmat(graph_med)
topmat = pvalmat(graph_top)