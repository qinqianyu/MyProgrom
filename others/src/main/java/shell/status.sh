#!/bin/bash

name=$(hostname)

ip=$(ip addr | grep '192.168.20' | awk -F "/" '{print $1}' | awk '{print $2}')

averageload=$(uptime | awk -F "load average: " '{print $2}')
averageload=${averageload//, /,}

physical_memory=$(top -n 1 | grep 'KiB Mem' | awk '{print $4}')
# shellcheck disable=SC2046
physical_memory=$(printf "%.5f" $(echo "scale=5;$physical_memory/1048576" | bc))

memory_used=$(top -n 1 | grep 'KiB Mem' | awk '{print $8}')
# shellcheck disable=SC2046
memory_used=$(printf "%.5f" $(echo "scale=5;$memory_used/1048576" | bc))

disk_capacity=$(df -k | awk 'NR>1{print $2,$3}' | awk -v t=0 '{t+=$1}END{print t}')
# shellcheck disable=SC2046
disk_capacity=$(printf "%.5f" $(echo "scale=5;$disk_capacity/1048576" | bc))

disk_used=$(df -k | awk 'NR>1{print $2,$3}' | awk -v t=0 '{t+=$2}END{print t}')
# shellcheck disable=SC2046
disk_used=$(printf "%.5f" $(echo "scale=5;$disk_used/1048576" | bc))

# 指定执行引擎
/usr/bin/expect <<EOF
    set timeout 30
    spawn /root/jxk/jiaoben/mysql-shell-8.0.19-linux-glibc2.12-x86-64bit/bin/mysqlsh -h 192.168.20.59 -u root -p123456
    expect "JS"
    send " \\\sql\r"
    expect "Switching to SQL mode... Commands end with ;"
    send "INSERT INTO big_data_scavenger.\`data_server_status\` ( \`name\`, \`ip\`, \`average_load\`, \`physical_memory\`, \`memory_used\`, \`disk_capacity\`, \`disk_used\`, \`create_at\`, \`modify_at\` ) VALUES ('$name','$ip','$averageload',$physical_memory,$memory_used,$disk_capacity,$disk_used,now(),now()) ON DUPLICATE KEY UPDATE \`name\` ='$name',\`average_load\` = '$averageload',\`physical_memory\` = $physical_memory,\`memory_used\` = $memory_used,\`disk_capacity\` = $disk_capacity,\`disk_used\` = $disk_used,\`modify_at\` = now();\r"
    expect "Query OK,"
    send "\\\q\r"
    expect eof
EOF
