import os
import psutil
import datetime
import time
from sys import *
import schedule

count = 0

def ProcessDisplay(log_dir = "ProcessLog"):
    listprocess = []

    if not os.path.exists(log_dir):
        try:
            os.mkdir(log_dir)
        except Exception:
            pass

    separator = "-" * 80

    log_time = datetime.datetime.now().strftime("%Y-%m-%d-%H-%M-%S")

    log_path = os.path.join(log_dir, "ProcessLog-%s.log"%(log_time))
    f = open(log_path, 'w')
    f.write(separator + "\n")
    f.write("Process Logger : "+log_time+ "\n")
    f.write(separator + "\n")

    for proc in psutil.process_iter():
        try:
            pinfo = proc.as_dict(attrs=['pid','name','username'])
            vms = proc.memory_info().vms / (1024*1024)
            pinfo['vms'] = vms
            listprocess.append(pinfo)
        
        except(psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
            pass

    for element in listprocess:
        f.write("%s\n" % element)

    global count
    count+=1

def Script_Terminator():
    global count
    if((count == 2)):
        print("Process Log Created {} times".format(count))
        exit()

def main():
    print("-------------------------------------------------------")

    print("Application Name : "+argv[0])

    print("-------------------------------------------------------")

    if(len(argv) < 2):
        print("Error : Insufficient arguments")
        print("Use --h or --H for Help")
        print("Use --u or --U for Usage")
        print("-------------------------------------------------------")
        exit()

    if(argv[1] == "--h" or argv[1] == "--H" or argv[1] == "-h" or argv[1] == "-H"):
        print("This Script is used to log record of running processes")
        print("-------------------------------------------------------")
        exit()

    if(argv[1] == "--u" or argv[1] == "--U" or argv[1] == "-u" or argv[1] == "-U"):
        print("Usage : ApplicationName AbsolutePath_Of_Directory")
        print("-------------------------------------------------------")
        exit()

    try:
        schedule.every(1).minutes.do(ProcessDisplay, log_dir = argv[1])
        schedule.every(1).minutes.do(Script_Terminator)
        while(True):
            schedule.run_pending()
            time.sleep(1)

    except ValueError:
        print("Error : Invalid datatype of Input")
    
    except Exception:
        print("Error : Invalid Input")
    

if(__name__ == "__main__"):
    main()